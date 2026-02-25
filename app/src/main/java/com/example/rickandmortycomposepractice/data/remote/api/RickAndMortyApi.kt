package com.example.rickandmortycomposepractice.data.remote.api

import com.example.rickandmortycomposepractice.data.remote.dto.CharacterDto
import com.example.rickandmortycomposepractice.data.remote.dto.RootDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(): RootDto

    @GET("character")
    suspend fun getCharactersByName(
        @Query("name") name: String
    ): RootDto

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto
}