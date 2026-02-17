package com.example.rickandmortycomposepractice.domain.repository

import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.model.CharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(name: String): CharacterResponse
    suspend fun getCharacterById(id: Int): Character?
}