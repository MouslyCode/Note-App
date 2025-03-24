package com.mouscode.noteapp.feature.note.domain.use_case

import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note:Note) {
        repository.deleteNote(note)
    }
}