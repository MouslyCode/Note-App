package com.mouscode.noteapp.feature.note.domain.use_case

import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int): Note? {
        return repository.getNotebyId(id = id)
    }
}