package com.example.rickandmortycomposepractice.data.repository

import android.util.Log
import com.example.rickandmortycomposepractice.data.model.Character
import com.example.rickandmortycomposepractice.data.network.RetrofitInstance

class CharacterRepository {

    private val api = RetrofitInstance.api

    suspend fun getCharacters(name: String): List<Character> {
        return try {
            val response = api.getCharacters(name)
            response.results
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters: ${e.message}")
            emptyList()
        }
    }

    suspend fun getCharacterById(id: Int): Character? {
        return try {
            api.getCharacter(id)
        } catch (_: Exception) {
            null
        }
    }
}