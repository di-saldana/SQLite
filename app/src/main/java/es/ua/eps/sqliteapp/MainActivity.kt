package es.ua.eps.sqliteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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

            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            buttonLogin.setOnClickListener {
                val intent = Intent(this@MainActivity, UserData::class.java)
                intent.putExtra(COLUMN_USERNAME, username)
//                    intent.putExtra(COLUMN_NOMBRE, user.nombre)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)

//                Toast.makeText(this@MainActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_SHORT).show()
//                }
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
                return true
            }
            R.id.restore -> {
                return true
            }
            R.id.manage -> {
                startActivity(Intent(this, UserManagement::class.java))
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}
