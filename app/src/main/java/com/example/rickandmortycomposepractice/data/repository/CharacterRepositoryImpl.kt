package com.example.rickandmortycomposepractice.data.repository

import android.util.Log
import com.example.rickandmortycomposepractice.data.network.RickAndMortyApi
import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.model.CharacterResponse
import com.example.rickandmortycomposepractice.domain.model.toCharacterDomain
import com.example.rickandmortycomposepractice.domain.model.toCharacterResponseDomain
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api : RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(name: String): CharacterResponse {
        return try {
            val response = api.getCharacters(name)
            response.toCharacterResponseDomain()
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters: ${e.message}")
            CharacterResponse(emptyList())
        }
    }

    override suspend fun getCharacterById(id: Int): Character? {
        return try {
            api.getCharacter(id).toCharacterDomain()
        } catch (_: Exception) {
            null
        }
    }
}