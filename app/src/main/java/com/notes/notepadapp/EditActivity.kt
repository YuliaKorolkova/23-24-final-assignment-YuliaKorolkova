package com.notes.notepadapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notes.notepadapp.data.model.NoteModel
import com.notes.notepadapp.databinding.ActivityNoteaddBinding

class EditActivity : AppCompatActivity() {
    private var _binding: ActivityNoteaddBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteaddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewAdd.text = "Edit note."
        val myArray = (application as AppNotepad).arrayOfNotes
        val myIndex = (application as AppNotepad).activeIndex
        val myElement = myArray[myIndex]
        val name = myElement.name
        val note = myElement.text
        val checked = myElement.done
        binding.editTextTextName.setText(name)
        binding.editTextTextDescription.setText(note)
        binding.imageViewAdd.setOnClickListener {
            val newItem = NoteModel(
                binding.editTextTextName.text.toString(),
                binding.editTextTextDescription.text.toString(),
                checked
            )
            (application as AppNotepad).arrayOfNotes[myIndex]=newItem
            val myIntent = Intent(this, MajorActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}