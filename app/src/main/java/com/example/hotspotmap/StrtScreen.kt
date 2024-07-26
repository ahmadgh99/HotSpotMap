package com.example.hotspotmap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StrtScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strt_screen)

        val btnSignUp = findViewById<Button>(R.id.btn_sign_up)
        val btnLogIn = findViewById<Button>(R.id.btn_log_in)
        val btnGuest = findViewById<Button>(R.id.btn_guest)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnGuest.setOnClickListener {
            val intent = Intent(this, MainMap::class.java)
            startActivity(intent)
        }
    }
}
