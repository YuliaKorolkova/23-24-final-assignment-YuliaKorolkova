package com.notes.notepadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.notes.notepadapp.data.model.NoteModel
import com.notes.notepadapp.databinding.ActivityNoteaddBinding

class NoteaddActivity : AppCompatActivity() {
    private var _binding: ActivityNoteaddBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteaddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageViewAdd.setOnClickListener {
            if (binding.editTextTextName.text.isNotBlank() && binding.editTextTextDescription.text.isNotBlank()) {
                (application as AppNotepad).arrayOfNotes.add(
                    NoteModel(
                        binding.editTextTextName.text.toString(),
                        binding.editTextTextDescription.text.toString(),
                        false
                    )
                )
                val myIntent = Intent(this, MajorActivity::class.java)
                startActivity(myIntent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Enter all text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}