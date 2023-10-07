package com.example.locationreminder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(private val db: AppDatabase): ViewModel(){

    fun insertNote(note: Note) {

        Log.d("LoggingStatus", "launching NoteViewModel")

        viewModelScope.launch {

            Log.d("LoggingStatus", "launched NoteViewModel")

            withContext(Dispatchers.IO) {
                db.noteDao().insertAll(note)

                Log.d("LoggingStatus", "note inserted in NoteViewModel launch block")
            }
        }
    }
}