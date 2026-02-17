package com.example.rickandmortycomposepractice.domain.usecase

import com.example.rickandmortycomposepractice.domain.model.CharacterResponse
import com.example.rickandmortycomposepractice.domain.model.toCharacterResponseDomain
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersByNameUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(name: String): CharacterResponse {
        return repository.getCharacters(name).toCharacterResponseDomain()
    }
}