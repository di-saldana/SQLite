package es.ua.eps.sqliteapp

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel

object DBManager {
    fun createBackup(context: Context) {
        val db = context.getDatabasePath("db.db")
        if (db.exists()) {
            val backupDir = File(Environment.getExternalStorageDirectory(), "DatabaseBackups")
            if (!backupDir.exists()) {
                backupDir.mkdirs()
            }

            val backupFileName = "db_backup.db"
            val backupFile = File(backupDir, backupFileName)

            try {
                copyFile(db, backupFile)
                Toast.makeText(context, "Backup done: $backupFileName", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Error creating backup, verify permissions", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Database doesn't exist", Toast.LENGTH_SHORT).show()
        }
    }

    fun restoreBackup(context: Context) {
        val backupDir = File(Environment.getExternalStorageDirectory(), "DatabaseBackups")
        val backupFile = File(backupDir, "db_backup.db")

        if (backupFile.exists()) {
            val dbPath = context.getDatabasePath("db_backup.db").absolutePath
            try {
                copyFile(backupFile, File(dbPath))
                Toast.makeText(context, "Database Restored", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Can't restore Database", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Backup doesn't exist", Toast.LENGTH_SHORT).show()
        }
    }

    private fun copyFile(sourceFile: File, destinationFile: File) {
        var sourceChannel: FileChannel? = null
        var destinationChannel: FileChannel? = null

        try {
            sourceChannel = FileInputStream(sourceFile).channel
            destinationChannel = FileOutputStream(destinationFile).channel
            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size())
        } finally {
            sourceChannel?.close()
            destinationChannel?.close()
        }
    }
}
