package com.example.locationreminder.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.locationreminder.db.Note

@Dao
interface NoteDao {
    @Insert
    fun insertAll(vararg note: Note)

    @Query("SELECT * FROM Note")
    fun getALl(): List<Note>
}