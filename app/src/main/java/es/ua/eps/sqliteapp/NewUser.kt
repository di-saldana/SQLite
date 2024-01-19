package es.ua.eps.sqliteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import es.ua.eps.sqliteapp.databinding.ActivityNewUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewUser : AppCompatActivity() {
    private lateinit var bindings: ActivityNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityNewUserBinding.inflate(layoutInflater)

        val db = databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
            .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

        with(bindings) {
            setContentView(root)

            newButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val nombre = nameEditText.text.toString()
                val email = emailEditText.text.toString()

                if (!username.isEmpty() && !password.isEmpty() && !nombre.isEmpty() && !email.isEmpty()) {
                    GlobalScope.launch {
                        val user = User(0, username, password, email, nombre)
                        db.userDAO().insert(user)
                    }

                    Toast.makeText(this@NewUser, "Usuario creado exitosamente", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@NewUser, "Favor completar los campos", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            closeButton.setOnClickListener {
                finish()
            }
        }
    }
}