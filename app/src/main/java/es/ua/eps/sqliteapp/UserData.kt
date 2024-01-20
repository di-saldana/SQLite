package es.ua.eps.sqliteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import es.ua.eps.sqliteapp.databinding.ActivityUserDataBinding

class UserData : AppCompatActivity() {

    private lateinit var bindings: ActivityUserDataBinding
    companion object {
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_NOMBRE_COMPLETO = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUserDataBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            nameTextView.text = intent.getStringExtra(COLUMN_NOMBRE_COMPLETO)
            usernameTextView.text = intent.getStringExtra(COLUMN_USERNAME)

            backButton.setOnClickListener {
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