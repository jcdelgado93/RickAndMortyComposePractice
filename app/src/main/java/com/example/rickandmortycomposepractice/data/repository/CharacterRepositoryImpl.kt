package com.example.rickandmortycomposepractice.data.repository

import android.util.Log
import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.data.network.RetrofitInstance
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {

    private val api = RetrofitInstance.api

    override suspend fun getCharacters(name: String): List<Character> {
        return try {
            val response = api.getCharacters(name)
            response.results
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getCharacterById(id: Int): Character? {
        return try {
            api.getCharacter(id)
        } catch (_: Exception) {
            null
        }
    }
}