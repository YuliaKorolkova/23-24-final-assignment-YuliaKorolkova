package com.notes.notepadapp

import android.app.Application
import com.notes.notepadapp.data.model.NoteModel

class AppNotepad:Application() {
    val arrayOfNotes= arrayListOf<NoteModel>()
    var activeIndex=0
    val arrayOfToDos= arrayListOf<NoteModel>()
    var activeIndexToDo=0
}