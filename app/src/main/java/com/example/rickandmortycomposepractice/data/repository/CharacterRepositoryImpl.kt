package com.example.rickandmortycomposepractice.data.repository

import android.util.Log
import com.example.rickandmortycomposepractice.data.entity.CharacterEntity
import com.example.rickandmortycomposepractice.data.entity.CharacterResponseEntity
import com.example.rickandmortycomposepractice.data.network.RickAndMortyApi
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api : RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(name: String): CharacterResponseEntity {
        return try {
            val response = api.getCharacters(name)
            response
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters: ${e.message}")
            CharacterResponseEntity(emptyList())
        }
    }

    override suspend fun getCharacterById(id: Int): CharacterEntity? {
        return try {
            api.getCharacter(id)
        } catch (_: Exception) {
            null
        }
    }
}