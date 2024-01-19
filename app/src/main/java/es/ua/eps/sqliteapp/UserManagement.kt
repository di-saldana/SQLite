package es.ua.eps.sqliteapp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import es.ua.eps.sqliteapp.databinding.ActivityUserManagementBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserManagement : AppCompatActivity() {
    private lateinit var bindings: ActivityUserManagementBinding
    private lateinit var db : AppDatabase
    private lateinit var users : List<User>

    lateinit var userDropdown : Spinner
    var pos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUserManagementBinding.inflate(layoutInflater)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

        with(bindings) {
            setContentView(root)

            userDropdown = selectUser
            GlobalScope.launch {
                listUsers()
            }

            newUserButton.setOnClickListener{
                val intent = Intent(this@UserManagement, NewUser::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            updateUserButton.setOnClickListener{
                val intent = Intent(this@UserManagement, UpdateUser::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            deleteUserButton.setOnClickListener{
                deleteUser()
            }

            listUserButton.setOnClickListener{
                val intent = Intent(this@UserManagement, ListUsers::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            backButton.setOnClickListener {
                finish()
            }
        }
    }

    suspend fun listUsers() {
        GlobalScope.launch {
            users = db.userDAO().loadAll()

            val userIDS = ArrayList<Int>()
            val userUsernames = ArrayList<String>()
            val userPasswords = ArrayList<String>()
            val userNames = ArrayList<String>()
            val userEmails = ArrayList<String>()

            for(user in users) {
                userIDS.add(user.uid)
                userUsernames.add(user.username)
                userPasswords.add(user.password)
                userNames.add(user.nombre)
                userEmails.add(user.email)
            }

            withContext(Dispatchers.Main) {
                val adapter = ArrayAdapter(this@UserManagement, R.layout.simple_spinner_item,
                    userUsernames)
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                userDropdown.adapter = adapter
                userDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                        position: Int, id: Long) {
                            pos = position
                        }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
        }
    }

    fun deleteUser() {
        GlobalScope.launch {
            if (pos >= 0 && pos < users.size) {
                val userToDelete = users[pos]

                db.userDAO().delete(userToDelete)

                listUsers()

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UserManagement, "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UserManagement, "Seleccione un usuario válido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}