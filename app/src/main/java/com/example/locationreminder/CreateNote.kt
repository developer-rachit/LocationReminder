package com.example.locationreminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room

class CreateNote : AppCompatActivity() {

    private lateinit var db : AppDatabase

    private lateinit var edTitle: EditText
    private lateinit var edNoteText: EditText
    private lateinit var btnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        edTitle = findViewById(R.id.ed_note_title)
        edNoteText = findViewById(R.id.ed_note_text)
        btnSave = findViewById(R.id.btnSave)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "location-reminder-database"
        ).build()

    }
}