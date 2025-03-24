package com.mouscode.noteapp.feature.note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}