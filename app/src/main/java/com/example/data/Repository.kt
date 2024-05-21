package com.example.data

import android.annotation.SuppressLint
import android.content.Context
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class Repository(context: Context) : RepoImple {
    var slist = mutableStateOf<ArrayList<SongModel>?>(null)
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getAllSongs(context: Context): MutableState<ArrayList<SongModel>?> {
        val templist = ArrayList<SongModel>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.RELATIVE_PATH,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
        )
      val cursor =  context.contentResolver.query(uri,projection,null,null,null)
        cursor.use {
           while (it!!.moveToNext()){
               val songModel = SongModel(
                   id = it.getString(0) ,
                   title = it.getString(1),
                   duration = it.getString(2) ,
                   filePath = it.getString(3),
                   artist =  it.getString(4),
                   data = it.getString(5)
               )
               templist.add(songModel)
           }
           slist.value=templist
        }
        return slist
    }


}