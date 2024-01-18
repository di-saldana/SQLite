package es.ua.eps.sqliteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
