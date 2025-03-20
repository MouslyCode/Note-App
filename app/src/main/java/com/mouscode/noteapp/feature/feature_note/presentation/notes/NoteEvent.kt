package com.mouscode.noteapp.feature.feature_note.presentation.notes

import com.mouscode.noteapp.feature.feature_note.domain.model.Note
import com.mouscode.noteapp.feature.feature_note.domain.util.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder): NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote: NoteEvent()
    object ToggleOrderSection: NoteEvent()
}