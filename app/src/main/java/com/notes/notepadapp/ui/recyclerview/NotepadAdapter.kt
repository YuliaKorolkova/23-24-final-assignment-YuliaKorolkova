package com.notes.notepadapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notes.notepadapp.MajorActivity
import com.notes.notepadapp.R
import com.notes.notepadapp.data.model.NoteModel

class NotepadAdapter(private val items: ArrayList<NoteModel>, val activity: MajorActivity) :
    RecyclerView.Adapter<NotepadAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById(R.id.textViewNamesss)
        val itemTextViewText: TextView = view.findViewById(R.id.textViewText)
        val editButton: ImageView = view.findViewById(R.id.imageViewRedact)
        val deleteButton: ImageView = view.findViewById(R.id.imageViewDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemTextView.text = item.name
        holder.itemTextViewText.text = item.text
        holder.deleteButton.setOnClickListener {
            activity.deleteElement(items.indexOf(item))
            notifyDataSetChanged()
        }
        holder.editButton.setOnClickListener {
            activity.editElement(items.indexOf(item))

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}