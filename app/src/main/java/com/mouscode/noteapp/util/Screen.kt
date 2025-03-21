package com.mouscode.noteapp.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
    object LoginScreen: Screen("login_screen")
}