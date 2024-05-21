package com.example.data

import android.content.Context
import androidx.compose.runtime.MutableState

interface RepoImple {
    suspend fun getAllSongs(context: Context): MutableState<ArrayList<SongModel>?>

}