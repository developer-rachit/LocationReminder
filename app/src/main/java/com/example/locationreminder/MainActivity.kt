package com.example.locationreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var btnCreateNote: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LoggingStatus", "entered onCreate MainActivity.kt")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreateNote = findViewById(R.id.btnCreateNote)

        btnCreateNote.setOnClickListener {
            Log.d("LoggingStatus", "create note button clicked, new activity CreateNote.kt called")

            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }
    }
}


