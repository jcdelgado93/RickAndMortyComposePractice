package com.example.rickandmortycomposepractice.domain.repository

import com.example.rickandmortycomposepractice.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(name: String): List<Character>
    suspend fun getCharacterById(id: Int): Character?
}