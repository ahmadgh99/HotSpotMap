package com.example.hotspotmap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            val intent = Intent(this, StrtScreen::class.java)
            startActivity(intent)
            finish() // Optionally finish this activity if you don't want to return to it
        }

        val backBtn = findViewById<ImageView>(R.id.image_12)
        backBtn.setOnClickListener {
            finish()
        }

    }
}
