package com.example.hotspotmap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.hotspotmap.LoginActivity
import com.example.hotspotmap.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var verifyEmail: EditText
    private lateinit var password: EditText
    private lateinit var verifyPassword: EditText
    private lateinit var signUpButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        verifyEmail = findViewById(R.id.verifyEmail)
        password = findViewById(R.id.password)
        verifyPassword = findViewById(R.id.verifyPassword)
        signUpButton = findViewById(R.id.signUpButton)
        backButton = findViewById(R.id.backButton)

        signUpButton.setOnClickListener {
            // Redirect to StartScreenActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            finish() // Closes the activity and goes back to the previous screen
        }
    }

    private fun isValidInput(): Boolean {
        // Add your input validation logic here
        if (firstName.text.isEmpty() || lastName.text.isEmpty() || email.text.isEmpty() ||
            verifyEmail.text.isEmpty() || password.text.isEmpty() || verifyPassword.text.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.text.toString() != verifyEmail.text.toString()) {
            Toast.makeText(this, "Emails do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.text.toString() != verifyPassword.text.toString()) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
