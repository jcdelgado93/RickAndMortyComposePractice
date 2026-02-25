package com.example.rickandmortycomposepractice.presentation.state

import com.example.rickandmortycomposepractice.domain.model.Character

data class CharacterDetailUiState(
    val character: Character? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)