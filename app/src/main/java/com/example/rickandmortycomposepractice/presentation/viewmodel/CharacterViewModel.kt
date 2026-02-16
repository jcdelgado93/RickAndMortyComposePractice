package com.example.rickandmortycomposepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import com.example.rickandmortycomposepractice.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CharactersUiState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val searchTerm: String = "",
    val isGridView: Boolean = false,
    val selectedCharacter: Character? = null
)

class CharacterViewModel(
    private val repository: CharacterRepository = CharacterRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        searchCharacters("")
    }

    fun searchCharacters(query: String) {
        _uiState.update { it.copy(searchTerm = query, isLoading = true) }

        viewModelScope.launch {
            try {
                val result = repository.getCharacters(query)
                _uiState.update {
                    it.copy(
                        characters = result,
                        isLoading = false
                    )
                }
            } catch (_: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun toggleViewType() {
        _uiState.update { it.copy(isGridView = !it.isGridView) }
    }

    fun selectCharacter(id: Int) {
        _uiState.update { it.copy(isLoading = true, selectedCharacter = null) }

        viewModelScope.launch {
            val character = repository.getCharacterById(id)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    selectedCharacter = character
                )
            }
        }
    }
}