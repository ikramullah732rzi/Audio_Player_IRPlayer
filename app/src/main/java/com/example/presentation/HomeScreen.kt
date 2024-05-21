package com.example.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.data.Repository
import com.example.data.SongModel
import com.example.domain.MainViewMoedl
import com.example.domain.MyFactory
import com.example.irplayer.DestinationScreen
import com.example.irplayer.R

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("StateFlowValueCalledInComposition", "PermissionLaunchedDuringComposition")
@Composable
fun HomeScreen(navController: NavHostController, mainViewMoedl: MainViewMoedl) {

    val songlist = mainViewMoedl.songlist.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(songlist.value) {
            it?.let {
                Sample(it,mainViewMoedl,navController)
            }

        }
    }

    /* val launcher = rememberLauncherForActivityResult(
         ActivityResultContracts.RequestPermission()
     ) { isGranted: Boolean ->
         if (isGranted) {
             // Permission Accepted: Do something
             Log.d("ExampleScreen","PERMISSION GRANTED")

         } else {
             // Permission Denied: Do something
             Log.d("ExampleScreen","PERMISSION DENIED")
         }
     }

     Button(
         onClick = {
             // Check permission
             when (PackageManager.PERMISSION_GRANTED) {
                 ContextCompat.checkSelfPermission(
                     context,
                     Manifest.permission.READ_EXTERNAL_STORAGE
                 ) -> {
                     // Some works that require permission
                     Log.d("ExampleScreen","Code requires permission")
                 }
                 else -> {
                     // Asking for permission
                     launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                 }
             }
         }
     ) {
         Text(text = "Check and Request Permission")
     }*/

}

@SuppressLint("DefaultLocale")
@Composable
fun Sample(songModel: SongModel, viewMoedl: MainViewMoedl, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp).clickable {
            viewMoedl.setSongModel(songModel)
                navController.navigate(DestinationScreen.DetailScreen.path){
                    popUpTo(DestinationScreen.DetailScreen.path){
                        inclusive=true
                    }
                }
            },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.music),
            contentDescription = "SONG ICON",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Column(
            Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = songModel.title!!, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Row {
                Text(text = songModel.duration!!)
                Text(text = songModel.artist!!)
            }
        }
        Image(imageVector = Icons.Filled.MoreVert, contentDescription = "SONG ICON")

    }
}