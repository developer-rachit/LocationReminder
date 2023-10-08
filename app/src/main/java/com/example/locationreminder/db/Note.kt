package com.example.locationreminder.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "noteTitle") var noteTitle : String,
    @ColumnInfo(name = "noteText") var noteText : String
)
