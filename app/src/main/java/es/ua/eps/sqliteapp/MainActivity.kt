package es.ua.eps.sqliteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase

import es.ua.eps.sqliteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindings: ActivityMainBinding

    companion object {
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_NOMBRE_COMPLETO = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
                .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

            buttonLogin.setOnClickListener {
                val username = editTextUsername.text.toString()
                val password = editTextPassword.text.toString()
                val user = db.userDAO().login(username, password)

                if(user != null) {
                    val intent = Intent(this@MainActivity, UserData::class.java)
                    intent.putExtra(COLUMN_USERNAME, user.username)
                    intent.putExtra(COLUMN_NOMBRE_COMPLETO, user.nombre)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)

                    Toast.makeText(this@MainActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_SHORT).show()
                }
            }

            buttonClose.setOnClickListener {
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
