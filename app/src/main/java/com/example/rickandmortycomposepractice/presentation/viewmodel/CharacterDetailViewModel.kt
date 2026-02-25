package com.example.rickandmortycomposepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycomposepractice.domain.usecase.GetCharactersByIdUseCase
import com.example.rickandmortycomposepractice.presentation.state.CharacterDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharactersByIdUseCase: GetCharactersByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    fun getCharacter(id: Int) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                getCharactersByIdUseCase(id).collect { character ->
                    _uiState.update {
                        it.copy(isLoading = false, character = character)
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}