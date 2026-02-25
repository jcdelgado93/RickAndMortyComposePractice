package com.example.rickandmortycomposepractice.data.remote.dto

import com.example.rickandmortycomposepractice.data.local.entity.CharacterEntity

data class RootDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)

data class InfoDto(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: String
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class OriginDto(
    val name: String,
    val url: String
)

data class LocationDto(
    val name: String,
    val url: String
)

fun CharacterDto.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
        episode = episode,
        url = url,
        created = created
    )
}