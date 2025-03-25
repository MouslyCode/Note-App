package com.mouscode.noteapp.feature.note.presentation.notes.util

import com.mouscode.noteapp.feature.note.domain.model.Note
import com.mouscode.noteapp.feature.note.domain.util.NoteOrder
import com.mouscode.noteapp.feature.note.domain.util.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)