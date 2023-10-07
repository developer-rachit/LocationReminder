package com.example.locationreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnCreateNote: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreateNote = findViewById(R.id.btnCreateNote)

        btnCreateNote.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }
    }
}