package com.example.domain

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.SongModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewMoedl(val repository: Repository, @SuppressLint("StaticFieldLeak") val context: Context) : ViewModel() {
    private val _songlist = MutableStateFlow<List<SongModel?>>(emptyList())
    val songlist = _songlist.asStateFlow()

    // private var songModel= SongModel()
    private  val _songmodel = MutableStateFlow<SongModel?>(null)
    val songModel = _songmodel.asStateFlow()

    init {
        getAllSongs()
    }

    fun getAllSongs() {
        viewModelScope.launch {
            _songlist.value = repository.getAllSongs(context = context).value!!
        }
    }



    fun setSongModel(songModel: SongModel) {
        _songmodel.value= songModel
    }




}