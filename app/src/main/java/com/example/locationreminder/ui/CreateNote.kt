package com.example.locationreminder.ui

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.locationreminder.db.AppDatabase
import com.example.locationreminder.db.Note
import com.example.locationreminder.R
import com.example.locationreminder.viewmodel.NoteViewModel
import com.example.locationreminder.viewmodel.NoteViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class CreateNote : AppCompatActivity() {

    //db
    private lateinit var db : AppDatabase
    private lateinit var note: Note

    //ui elements
    private lateinit var btnSave: Button
    private lateinit var edTitle: EditText
    private lateinit var edNoteText: EditText
    private lateinit var btnSetTime: Button
    private lateinit var tvTime: TextView

    private lateinit var calendar: Calendar

    //viewmodel
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        // ui elements initialization
        edTitle = findViewById(R.id.ed_note_title)
        edNoteText = findViewById(R.id.ed_note_text)
        btnSave = findViewById(R.id.btnSave)
        btnSetTime = findViewById(R.id.btnSetTime)
        tvTime = findViewById(R.id.tvTime)

        calendar = Calendar.getInstance()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "location-reminder-database"
        ).build()

        //viewmodel initialization
        noteViewModel = ViewModelProvider(this, NoteViewModelFactory(db)).get(NoteViewModel::class.java)

        btnSetTime.setOnClickListener {
            showTimePickerDialog()
        }

        //saving note
        btnSave.setOnClickListener {
            // empty text logic
            if(edNoteText.text.toString() != "") {


                Log.d("LoggingStatus", "tvTime view string when save note button is clicked -> ${tvTime.text}")
                note = Note(Date().toString(), edTitle.text.toString(), edNoteText.text.toString(), tvTime.text.toString())

                noteViewModel.insertNote(note)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Time Picker
    private fun showTimePickerDialog(){

        val timePicker = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOdDay, minute ->
                // time selection
                calendar.set(Calendar.HOUR_OF_DAY, hourOdDay)
                calendar.set(Calendar.MINUTE, minute)

                // format time and display in text view
                val timeFormat = SimpleDateFormat("hh:mm a")
                val selectedTime = timeFormat.format(calendar.time)

                tvTime.text = selectedTime

                Log.d("LoggingStatus", "tvTime view string in showTimePickerDialog -> ${tvTime.text}")

            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePicker.show()
    }
}