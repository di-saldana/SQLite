package es.ua.eps.sqliteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import es.ua.eps.sqliteapp.databinding.ActivityListUsersBinding

class ListUsers : AppCompatActivity() {
    private lateinit var bindings: ActivityListUsersBinding

    private var adapter:UserListAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityListUsersBinding.inflate(layoutInflater)

        with(bindings) {
            setContentView(root)

            recyclerView.layoutManager = LinearLayoutManager(this@ListUsers)
            adapter = UserListAdapter()
            recyclerView.adapter = adapter

            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db.db")
                .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()
            val userList = db.userDAO().loadAll()
            adapter?.addItems(userList)

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