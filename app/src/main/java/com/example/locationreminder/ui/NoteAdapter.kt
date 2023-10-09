package com.example.locationreminder.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.locationreminder.R
import com.example.locationreminder.db.Note

class NoteAdapter(private val notes: List<Note>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNoteTitle: TextView = itemView.findViewById(R.id.tvNoteTitle)
        val tvNoteText: TextView = itemView.findViewById(R.id.tvNoteText)
        val tvNoteCreatd: TextView = itemView.findViewById(R.id.tvCreatedTime)
        val tvRemindTime: TextView = itemView.findViewById(R.id.tvRemindTime)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = notes[position]
        holder.tvNoteTitle.text = note.noteTitle
        holder.tvNoteText.text = note.noteText
        holder.tvNoteCreatd.text = note.uid
        holder.tvRemindTime.text = note.noteRemindTime
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}