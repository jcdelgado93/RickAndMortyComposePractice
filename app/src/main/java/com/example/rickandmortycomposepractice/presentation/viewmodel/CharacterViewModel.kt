package com.example.rickandmortycomposepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycomposepractice.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortycomposepractice.domain.usecase.GetCharactersByIdUseCase
import com.example.rickandmortycomposepractice.domain.usecase.GetCharactersByNameUseCase
import com.example.rickandmortycomposepractice.presentation.state.CharactersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    private val getCharactersByIdUseCase: GetCharactersByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private var detailJob: Job? = null

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        searchJob?.cancel()
        _uiState.update { it.copy(isLoading = true) }
        searchJob = viewModelScope.launch {
            try {
                getAllCharactersUseCase().collect { response ->
                    _uiState.update {
                        it.copy(
                            characters = response,
                            isLoading = false
                        )
                    }
                }
            } catch (_: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun searchCharactersByName(query: String) {
        _uiState.update { it.copy(searchTerm = query, isLoading = true) }

        if (query.isBlank()) {
            getAllCharacters()
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                getCharactersByNameUseCase(query).collect { response ->
                    _uiState.update {
                        it.copy(
                            characters = response,
                            isLoading = false
                        )
                    }
                }
            } catch (_: Exception) {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun selectCharacter(id: Int) {
        detailJob?.cancel()
        _uiState.update { it.copy(isLoading = true, selectedCharacter = null) }
        detailJob = viewModelScope.launch {
            getCharactersByIdUseCase(id).collect { character ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        selectedCharacter = character
                    )
                }
            }
        }
    }

    fun toggleViewType() {
        _uiState.update { it.copy(isGridView = !it.isGridView) }
    }
}