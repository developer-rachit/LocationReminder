package com.example.locationreminder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import com.example.locationreminder.R
import com.example.locationreminder.db.AppDatabase
import com.example.locationreminder.db.Note
import com.example.locationreminder.viewmodel.NoteViewModel
import com.example.locationreminder.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db : AppDatabase
    //viewmodel
    private lateinit var noteViewModel: NoteViewModel

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

        //here our note is saved in db


        //recycler view

        Log.d("LoggingStatus", "btnCreatNote completed")

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "location-reminder-database"
        ).build()

        //initialize recycler view
        val recyclerView: RecyclerView = findViewById(R.id.noteRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        Log.d("LoggingStatus", "recycler view initialized and db instance created")

        var notesList = mutableListOf<Note>()

        val adapter = NoteAdapter(notesList)
        recyclerView.adapter = adapter

        //viewmodel initialization
        noteViewModel = ViewModelProvider(this@MainActivity, NoteViewModelFactory(db)).get(NoteViewModel::class.java)




        noteViewModel.getNotesLiveData().observe(this) { notes ->
            notesList.clear() // Clear the existing list
            notesList.addAll(notes) // Add the new notes to the list
            adapter.notifyDataSetChanged()
        }

        Log.d("LoggingStatus", "adapter set, view model initialized $notesList")

        noteViewModel.getNotes()
    }
}


