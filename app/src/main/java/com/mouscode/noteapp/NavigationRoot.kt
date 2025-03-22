package com.mouscode.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.mouscode.noteapp.feature.feature_auth.presentation.LoginScreen
import com.mouscode.noteapp.feature.feature_auth.presentation.LoginViewModel
import com.mouscode.noteapp.feature.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.mouscode.noteapp.feature.feature_note.presentation.notes.NoteScreen
import com.mouscode.noteapp.util.Screen
import kotlinx.serialization.Serializable

@Serializable
data class LoggedInRoute(val username: String)

@Composable
fun NavigationRoot(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(route = Screen.LoginScreen.route) {
            val viewModel = viewModel<LoginViewModel>()
            LoginScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent
            )
        }
        composable<LoggedInRoute> {
            val username = it.toRoute<LoggedInRoute>().username
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Hello $username")
            }
        }
        composable(route = Screen.NotesScreen.route){
            NoteScreen(navController = navController)
        }
        composable (
            route = Screen.AddEditNoteScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
    }
}