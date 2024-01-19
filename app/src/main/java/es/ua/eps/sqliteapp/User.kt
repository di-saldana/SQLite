package es.ua.eps.sqliteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0,

    @ColumnInfo(name = "username")
    var username : String,

    @ColumnInfo(name = "password")
    var password : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "nombre")
    var nombre : String){
}
