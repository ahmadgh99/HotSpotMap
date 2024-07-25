package com.example.hotspotmap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        backButton = findViewById(R.id.backButton)

        loginButton.setOnClickListener {
            // Handle login logic here or redirect to ProfileActivity
            val intent = Intent(this, MainMap::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish() // Closes the activity and goes back to the previous screen
        }
    }
}
