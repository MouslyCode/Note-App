package com.mouscode.noteapp.feature.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mouscode.noteapp.feature.auth.domain.usecase.AuthUseCases
import com.mouscode.noteapp.feature.auth.presentation.util.AuthEvent
import com.mouscode.noteapp.feature.auth.presentation.util.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()


    

}