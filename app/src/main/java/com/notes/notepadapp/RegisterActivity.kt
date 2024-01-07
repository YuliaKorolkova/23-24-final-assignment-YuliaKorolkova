package com.notes.notepadapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.notes.notepadapp.data.DatabaseHelper
import com.notes.notepadapp.screens.LoginScreen
import com.notes.notepadapp.screens.RegisterScreen
import com.notes.notepadapp.ui.theme.NotepadAppTheme

class RegisterActivity : ComponentActivity() {
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
                    RegisterScreen(this@RegisterActivity)
                }
            }
        }
    }

    fun performRegister(context: Context, username: String, password: String,passwordSecond: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if(password==passwordSecond){
                Toast.makeText(context, "Registered $username", Toast.LENGTH_SHORT).show()
                dbHelper.registerUser(username,password)
            }else{
                Toast.makeText(context, "There are different passwords", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter both username and password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}

