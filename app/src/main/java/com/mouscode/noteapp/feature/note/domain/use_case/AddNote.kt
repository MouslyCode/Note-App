package com.mouscode.noteapp.feature.note.domain.use_case

import com.mouscode.noteapp.feature.note.domain.model.InvalidNoteException
import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote (
    private val repository: NoteRepository
){
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repository.inserNote(note)
    }
}