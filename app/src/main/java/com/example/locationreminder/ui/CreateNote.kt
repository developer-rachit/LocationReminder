package com.example.locationreminder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.locationreminder.db.AppDatabase
import com.example.locationreminder.db.Note
import com.example.locationreminder.R
import com.example.locationreminder.viewmodel.NoteViewModel
import com.example.locationreminder.viewmodel.NoteViewModelFactory
import java.util.Date

class CreateNote : AppCompatActivity() {

    //db
    private lateinit var db : AppDatabase
    private lateinit var note: Note

    //ui elements
//    private lateinit var edTitle: EditText
//    private lateinit var edNoteText: EditText
    private lateinit var btnSave: Button

    //viewmodel
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("LoggingStatus", "entered CreaNote.kt onCreate method")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

//        edTitle = findViewById(R.id.ed_note_title)
//        edNoteText = findViewById(R.id.ed_note_text)
        btnSave = findViewById(R.id.btnSave)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "location-reminder-database"
        ).build()

        //viewmodel initialization
        noteViewModel = ViewModelProvider(this, NoteViewModelFactory(db)).get(NoteViewModel::class.java)


        Log.d("LoggingStatus", "just before save button is clicked")

        btnSave.setOnClickListener {

            var edTitle = findViewById<EditText>(R.id.ed_note_title)
            var edNoteText = findViewById<EditText>(R.id.ed_note_text)

            note = Note(Date().toString(), edTitle.text.toString(), edNoteText.text.toString())

            Log.d("LoggingStatus", "Save button clicked.")
            noteViewModel.insertNote(note)
            Log.d("LoggingStatus", "view model is called and note is inserted")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}