package com.mouscode.noteapp.feature_note.domain.repository

import com.mouscode.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNotebyId(id: Int): Note?

    suspend fun inserNote(note: Note)

    suspend fun deleteNote(note: Note)
}