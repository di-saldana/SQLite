package es.ua.eps.sqliteapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO

    companion object{
        @Volatile
        private var DB: AppDatabase ?= null
    }
}

