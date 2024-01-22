package es.ua.eps.sqliteapp

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.database.sqlite.SQLiteDatabase

class MyContentProvider : ContentProvider() {

    companion object{
        private const val TABLE_NAME = "User"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = DBHelper(getContext())
        db = helper.writableDatabase

        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.es.eps.ua.sqliteapp.provider.$TABLE_NAME"
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        db.insert(TABLE_NAME, null, p1)
        requireContext().contentResolver.notifyChange(p0, null)
        return p0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        var count = db.delete(TABLE_NAME,  p1, p2)
        requireContext().contentResolver.notifyChange(p0, null)
        return count
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        var count = db.update(TABLE_NAME, p1, p2, p3)
        requireContext().contentResolver.notifyChange(p0, null)
        return count
    }
}