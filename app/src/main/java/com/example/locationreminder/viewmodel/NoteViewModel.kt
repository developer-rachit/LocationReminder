package com.example.locationreminder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationreminder.db.AppDatabase
import com.example.locationreminder.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(private val db: AppDatabase): ViewModel(){

    private val notesLiveData = MutableLiveData<List<Note>>()
    lateinit  var listOfNote: List<Note>

    fun getNotesLiveData(): LiveData<List<Note>> {
        return notesLiveData
    }


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

    fun getNotes() {
                viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var notesList = db.noteDao().getALl()
                Log.d("LoggingStatus", "in NoteViewModel.kt $notesList")

                notesLiveData.postValue(notesList)

            }
        }
    }
}