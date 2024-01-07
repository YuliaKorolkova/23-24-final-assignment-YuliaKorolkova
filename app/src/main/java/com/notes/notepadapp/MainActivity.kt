package com.notes.notepadapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.notes.notepadapp.data.DatabaseHelper
import com.notes.notepadapp.screens.LoginScreen
import com.notes.notepadapp.ui.theme.NotepadAppTheme


class MainActivity : ComponentActivity() {
     lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DatabaseHelper(this)
        setContent {
            NotepadAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(this@MainActivity)
                }
            }
        }
    }

    fun performLogin(context: Context, username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (authenticateUser(username, password)) {
                val myIntent = Intent(this@MainActivity, MajorActivity::class.java)
                startActivity(myIntent)
                finish()
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter both username and password", Toast.LENGTH_SHORT).show()
        }
    }

    fun authenticateUser(username: String, password: String): Boolean {
        val hashedPassword = dbHelper.getUserPassword(username)
        return dbHelper.hashPassword(password) == hashedPassword
    }
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}

