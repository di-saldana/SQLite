package es.ua.eps.sqliteapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun loadAll(): List<User>

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByUserId(userIds: List<Int>): List<User>

//    @Query("SELECT * FROM user where username = :username AND password = :password")
//    fun login(username: String, password: String): User?

    @Insert
    fun insertAll(vararg users: User)

    @Update
    fun update(user: User)

    @Insert
    fun insert(user: User?)

    @Delete
    fun delete(user: User?)
}
