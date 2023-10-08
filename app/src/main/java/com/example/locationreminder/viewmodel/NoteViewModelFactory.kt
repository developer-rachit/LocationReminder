package com.example.locationreminder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.locationreminder.db.AppDatabase

class NoteViewModelFactory(private val db: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}