package com.mouscode.noteapp.feature.note.presentation.notes

import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.util.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}