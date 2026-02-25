package com.example.rickandmortycomposepractice.data.repository

import android.util.Log
import com.example.rickandmortycomposepractice.data.local.dao.CharacterDao
import com.example.rickandmortycomposepractice.data.local.entity.toCharacterDomain
import com.example.rickandmortycomposepractice.data.remote.api.RickAndMortyApi
import com.example.rickandmortycomposepractice.data.remote.dto.toCharacterEntity
import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: CharacterDao,
    private val api: RickAndMortyApi
) : CharacterRepository {

    override fun getAllCharacters(): Flow<List<Character>> {
        return dao.getAllCharacters()
            .onStart { fetchAllCharactersFromApi() }
            .map { entities -> entities.map { it.toCharacterDomain() }
        }
    }

    override fun getCharactersByName(name: String): Flow<List<Character>> {
        return dao.getCharactersByName(name)
            .onStart { fetchCharacterByNameFromApi(name) }
            .map { entities -> entities.map { it.toCharacterDomain() }
        }
    }

    override fun getCharacterById(id: Int): Flow<Character?> {
        return dao.getCharacterById(id)
            .onStart { fetchCharacterByIdFromApi(id) }
            .map { entity ->
            entity?.toCharacterDomain()
        }
    }

    private suspend fun fetchAllCharactersFromApi() {
        try {
            val responseDto = api.getAllCharacters()
            val characters = responseDto.results.map { it.toCharacterEntity() }
            dao.insertCharacters(characters)
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching characters from API", e)
        }
    }

    private suspend fun fetchCharacterByNameFromApi(name: String) {
        try {
            val responseDto = api.getCharactersByName(name)
            val characters = responseDto.results.map { it.toCharacterEntity() }
            dao.insertCharacters(characters)
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching character by name from API", e)
        }
    }


    private suspend fun fetchCharacterByIdFromApi(id: Int) {
        try {
            val responseDto = api.getCharacterById(id)
            dao.insertCharacters(listOf(responseDto.toCharacterEntity()))
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Error fetching character by ID from API", e)
        }
    }
}