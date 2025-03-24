package com.mouscode.noteapp.feature.note.data.repository

import com.mouscode.noteapp.feature.note.data.data_source.NoteDao
import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl (
    private val dao: NoteDao
): NoteRepository{

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNotebyId(id: Int): Note? {
        return dao.getNotebyId(id = id)
    }

    override suspend fun inserNote(note: Note) {
        return dao.inserNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}