package com.notes.notepadapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    fun registerUser(username: String, password: String) {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        val hashedPassword = hashPassword(password)

        val values = ContentValues()
        values.put(DatabaseHelper.COLUMN_USERNAME, username)
        values.put(DatabaseHelper.COLUMN_PASSWORD, hashedPassword)

        // Insert the new user into the database
        db.insert(DatabaseHelper.TABLE_NAME, null, values)

        db.close()
    }
    fun hashPassword(password: String): String {
        return password
    }
    companion object {
        const val DATABASE_NAME = "user_credentials.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "users"
        const val COLUMN_ID = "_id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }

    private val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUMN_USERNAME TEXT," +
            "$COLUMN_PASSWORD TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun getUserPassword(username: String): String {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_PASSWORD),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        var hashedPassword = ""

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_PASSWORD)

            if (columnIndex >= 0) {
                hashedPassword = cursor.getString(columnIndex)
            }
        }

        cursor.close()
        db.close()

        return hashedPassword
    }
}