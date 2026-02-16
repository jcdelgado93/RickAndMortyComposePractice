package com.example.rickandmortycomposepractice.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results") val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val gender: String
)