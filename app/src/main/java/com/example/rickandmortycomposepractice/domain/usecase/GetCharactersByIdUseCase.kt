package com.example.rickandmortycomposepractice.domain.usecase

import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.model.toCharacterDomain
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Character? {
        return repository.getCharacterById(id)?.toCharacterDomain()
    }
}