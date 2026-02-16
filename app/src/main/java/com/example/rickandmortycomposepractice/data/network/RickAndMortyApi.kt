package com.example.rickandmortycomposepractice.data.network

import com.example.rickandmortycomposepractice.data.model.CharacterResponse
import com.example.rickandmortycomposepractice.data.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): Character
}