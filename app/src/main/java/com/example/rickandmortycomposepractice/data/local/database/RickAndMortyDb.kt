package com.example.rickandmortycomposepractice.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortycomposepractice.data.local.converters.Converters
import com.example.rickandmortycomposepractice.data.local.dao.CharacterDao
import com.example.rickandmortycomposepractice.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RickAndMortyDb : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}