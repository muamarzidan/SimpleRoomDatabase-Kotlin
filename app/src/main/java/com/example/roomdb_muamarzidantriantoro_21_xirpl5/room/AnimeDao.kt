package com.example.roomdb_muamarzidantriantoro_21_xirpl5.room

import androidx.room.*

@Dao
interface AnimeDao {

    @Insert
    suspend fun addAnime(anime: Anime)

    @Update
    suspend fun updateAnime(anime: Anime)

    @Delete
    suspend fun deleteAnime(anime: Anime)

    @Query ("SELECT * FROM anime")
    suspend fun getAnimes():List<Anime>

    @Query ("SELECT * FROM anime WHERE id=:anime_id")
    suspend fun getAnime(anime_id: Int): List<Anime>


}