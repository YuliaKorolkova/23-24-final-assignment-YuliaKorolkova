package com.notes.notepadapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.notes.notepadapp.CheckActivity
import com.notes.notepadapp.R
import com.notes.notepadapp.data.model.NoteModel

class ToDoAdapter(private val items: ArrayList<NoteModel>, val activity: CheckActivity) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById(R.id.textViewNameForCheck)
        val checks: CheckBox = view.findViewById(R.id.checkBox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.check_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.itemTextView.text = item.name
        holder.checks.isChecked=item.done
        holder.checks.setOnCheckedChangeListener { _, isChecked ->
            if(!activity.binding.recCheck.isComputingLayout && activity.binding.recCheck.scrollState == SCROLL_STATE_IDLE){
                activity.isCheckedOrNot(items.indexOf(item),isChecked)
                notifyDataSetChanged()
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}