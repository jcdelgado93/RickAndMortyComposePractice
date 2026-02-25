package com.example.rickandmortycomposepractice.domain.repository

import com.example.rickandmortycomposepractice.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<List<Character>>
    fun getCharactersByName(name: String): Flow<List<Character>>
    fun getCharacterById(id: Int): Flow<Character?>
}