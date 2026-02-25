package com.example.rickandmortycomposepractice.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortycomposepractice.data.remote.dto.LocationDto
import com.example.rickandmortycomposepractice.data.remote.dto.OriginDto
import com.example.rickandmortycomposepractice.domain.model.Character
import com.example.rickandmortycomposepractice.domain.model.Location
import com.example.rickandmortycomposepractice.domain.model.Origin

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey val id: Int,
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

fun CharacterEntity.toCharacterDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = Origin(name = origin.name, url = origin.url),
        location = Location(name = location.name, url = location.url),
        image = image,
        episode = episode,
        url = url,
        created = created
    )
}