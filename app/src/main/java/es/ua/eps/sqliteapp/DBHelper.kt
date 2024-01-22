package es.ua.eps.sqliteapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context?)  : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db.db"
        private const val TABLE_NAME = "User"
        private const val ID = "uid"
        private const val NOMBRE_USUARIO = "username"
        private const val PASSWORD = "password"
        private const val NOMBRE_COMPLETO = "nombre"
        private const val EMAIL = "email"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = ("CREATE TABLE ${TABLE_NAME}" +
                "(${ID} INTEGER PRIMARY KEY AUTOINCREMENT" +
                " ,${NOMBRE_USUARIO} TEXT" +
                " ,${PASSWORD} TEXT" +
                " ,${EMAIL} TEXT" +
                " ,${NOMBRE_COMPLETO} TEXT" +
                ")")
        db?.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion == 1) {
            db?.execSQL("ALTER TABLE ${TABLE_NAME} ADD ${EMAIL} TEXT")
        }
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(db)
    }
}