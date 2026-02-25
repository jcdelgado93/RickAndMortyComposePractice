package com.example.rickandmortycomposepractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycomposepractice.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortycomposepractice.domain.usecase.GetCharactersByNameUseCase
import com.example.rickandmortycomposepractice.presentation.state.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharactersByNameUseCase: GetCharactersByNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        getAllCharacters()
    }

    fun searchCharactersByName(query: String) {
        _uiState.update { it.copy(searchTerm = query) }

        if (query.isBlank()) {
            getAllCharacters()
            return
        }

        searchJob?.cancel()
        _uiState.update { it.copy(isLoading = true) }

        searchJob = viewModelScope.launch {
            try {
                getCharactersByNameUseCase(query).collect { response ->
                    _uiState.update {
                        it.copy(characters = response, isLoading = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun getAllCharacters() {
        searchJob?.cancel()
        _uiState.update { it.copy(isLoading = true) }

        searchJob = viewModelScope.launch {
            try {
                getAllCharactersUseCase().collect { response ->
                    _uiState.update {
                        it.copy(characters = response, isLoading = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun toggleViewType() {
        _uiState.update { it.copy(isGridView = !it.isGridView) }
    }
}