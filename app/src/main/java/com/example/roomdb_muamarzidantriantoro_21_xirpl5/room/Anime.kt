package com.example.roomdb_muamarzidantriantoro_21_xirpl5.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Anime (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title:  String,
    val desc: String

    )
