package es.ua.eps.sqliteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import es.ua.eps.sqliteapp.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    private lateinit var bindings: ActivityUpdateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUpdateUserBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "db.db"
            ).allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

            updateButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val nombre = nameEditText.text.toString()
                val email = emailEditText.text.toString()

                val existingUser = db.userDAO().loadOneByUsername(username)

                if (existingUser != null) {
                    existingUser.password = password
                    existingUser.nombre = nombre
                    existingUser.email = email

                    db.userDAO().update(existingUser)

                    Toast.makeText(
                        this@UpdateUser,
                        "User updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@UpdateUser,
                        "User not found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            closeButton.setOnClickListener {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create -> {
                val dbManager = DBManager
                dbManager.createBackup(applicationContext)
                return true
            }
            R.id.restore -> {
                val dbManager = DBManager
                dbManager.restoreBackup(applicationContext)
                return true
            }
            R.id.manage -> {
                val intent = Intent(this, UserManagement::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
