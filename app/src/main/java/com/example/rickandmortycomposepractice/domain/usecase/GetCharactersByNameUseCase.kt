package com.example.rickandmortycomposepractice.domain.usecase

import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(name: String) = repository.getCharacters(name)
}