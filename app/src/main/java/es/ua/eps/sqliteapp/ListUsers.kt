package es.ua.eps.sqliteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}