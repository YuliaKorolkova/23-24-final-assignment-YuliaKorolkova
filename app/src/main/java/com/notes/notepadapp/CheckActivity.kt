package com.notes.notepadapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.notes.notepadapp.data.model.NoteModel
import com.notes.notepadapp.databinding.ActivityCheckBinding
import com.notes.notepadapp.ui.recyclerview.NotepadAdapter
import com.notes.notepadapp.ui.recyclerview.ToDoAdapter

class CheckActivity : AppCompatActivity() {
    private var _binding: ActivityCheckBinding? = null
    val binding get() = _binding!!
    var adapter:ToDoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter=ToDoAdapter((application as AppNotepad).arrayOfToDos, this@CheckActivity)

        binding.recCheck.layoutManager = LinearLayoutManager(this@CheckActivity)
        binding.recCheck.adapter = adapter
        setContentView(binding.root)
        binding.buttonDeleteDone.setOnClickListener {
            deletingToDos()
        }
        binding.buttonAddTodo.setOnClickListener {
                val myIntent = Intent(this, AddtodoActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val myIntent = Intent(this, MajorActivity::class.java)
        startActivity(myIntent)
        finish()
    }
    private fun deletingToDos() {
        if (!binding.recCheck.isComputingLayout){
            if ((application as AppNotepad).arrayOfToDos.size != 0) {
                for (item in (application as AppNotepad).arrayOfToDos) {
                    if (item.done) {
                        val index = (application as AppNotepad).arrayOfToDos.indexOf(item)
                        (application as AppNotepad).arrayOfToDos.removeAt(index)
                        adapter?.notifyDataSetChanged()
                        deletingToDos()
                        break
                    }
                }

            }
        }
    }

    fun isCheckedOrNot(index: Int, isChecked: Boolean) {
        if(index!=-1){
            if (!binding.recCheck.isComputingLayout)
            {
                (application as AppNotepad).arrayOfToDos[index].done = isChecked
                adapter?.notifyDataSetChanged()
            }

        }

    }
}