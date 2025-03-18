package com.mouscode.noteapp.feature_note.presentation.notes

import com.mouscode.noteapp.feature_note.domain.model.Note
import com.mouscode.noteapp.feature_note.domain.util.NoteOrder
import com.mouscode.noteapp.feature_note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)
