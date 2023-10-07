package com.example.locationreminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "note_title") val note_title : String?,
    @ColumnInfo(name = "note_text") val note_text : String?
)
