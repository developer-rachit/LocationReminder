package com.example.locationreminder

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteDao {
    @Insert
    fun insertAll(vararg note: Note)
}