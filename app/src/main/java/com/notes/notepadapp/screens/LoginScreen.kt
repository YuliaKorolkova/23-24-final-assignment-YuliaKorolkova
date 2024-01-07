package com.notes.notepadapp.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.notepadapp.MainActivity
import com.notes.notepadapp.R
import com.notes.notepadapp.RegisterActivity

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(activity: MainActivity) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginEnabled by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Login",
            fontWeight = FontWeight.Bold, color = colorResource(R.color.teal_700),
            fontSize = 30.sp
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(

            value = username,
            onValueChange = {
                username = it
                isLoginEnabled = it.isNotBlank() && password.isNotBlank()
            },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
                isLoginEnabled = it.isNotBlank() && username.isNotBlank()
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (isLoginEnabled) {
                        keyboardController?.hide()
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        TextButton(
            onClick = {
                activity.performLogin(context, username, password)
                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp).background(colorResource(R.color.teal_700)),
            enabled = isLoginEnabled && !isLoading
        ) {
                Text("Login" ,fontWeight = FontWeight.Bold, color = colorResource(R.color.white),
                    fontSize = 25.sp, modifier = Modifier.background(colorResource(R.color.transparent)))
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                val myIntent = Intent(activity, RegisterActivity::class.java)
                activity.startActivity(myIntent)
                activity.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Sign up!", color = colorResource(R.color.teal_700))
        }
    }

}



