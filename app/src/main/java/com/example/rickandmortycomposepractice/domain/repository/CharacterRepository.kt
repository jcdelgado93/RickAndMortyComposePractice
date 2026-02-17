package com.example.rickandmortycomposepractice.domain.repository

import com.example.rickandmortycomposepractice.data.entity.CharacterEntity
import com.example.rickandmortycomposepractice.data.entity.CharacterResponseEntity

interface CharacterRepository {
    suspend fun getCharacters(name: String): CharacterResponseEntity
    suspend fun getCharacterById(id: Int): CharacterEntity?
}