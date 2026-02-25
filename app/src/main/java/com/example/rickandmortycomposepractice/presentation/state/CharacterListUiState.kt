package com.example.rickandmortycomposepractice.presentation.state

import com.example.rickandmortycomposepractice.domain.model.Character

data class CharacterListUiState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val searchTerm: String = "",
    val isGridView: Boolean = false,
    val error: String? = null
)