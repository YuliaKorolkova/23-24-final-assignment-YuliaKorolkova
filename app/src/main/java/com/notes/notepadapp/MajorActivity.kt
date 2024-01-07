package com.notes.notepadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.notes.notepadapp.data.model.NoteModel
import com.notes.notepadapp.databinding.ActivityMajorBinding
import com.notes.notepadapp.ui.recyclerview.NotepadAdapter

class MajorActivity : AppCompatActivity() {
    private var _binding: ActivityMajorBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMajorBinding.inflate(layoutInflater)
        binding.recTest.layoutManager = LinearLayoutManager(this@MajorActivity)
        binding.recTest.adapter = NotepadAdapter((application as AppNotepad).arrayOfNotes, this@MajorActivity)
        setContentView(binding.root)
        binding.buttonAdd.setOnClickListener {
            val myIntent = Intent(this, NoteaddActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.button2.setOnClickListener {
            val myIntent = Intent(this, CheckActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
    fun deleteElement(index:Int){
        (application as AppNotepad).arrayOfNotes.removeAt(index)
    }

    fun editElement(index:Int){
        (application as AppNotepad).activeIndex=index
        val myIntent = Intent(this, EditActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}