package com.example.irplayer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.Repository
import com.example.domain.MainViewMoedl
import com.example.domain.MyFactory
import com.example.irplayer.ui.theme.IRPlayerTheme
import com.example.presentation.DetailScreen
import com.example.presentation.HomeScreen
import com.example.presentation.SplishScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewMoedl = ViewModelProvider(this, factory = MyFactory(Repository(this),this)).get(MainViewMoedl::class.java)

        /*  enableEdgeToEdge()*/
        setContent {
            IRPlayerTheme {
                Main(mainViewMoedl)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Main(mainViewMoedl: MainViewMoedl) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationScreen.HomeScreen.path) {
        composable(DestinationScreen.SplishScreen.path) {
            SplishScreen(navController)
        }
        composable(DestinationScreen.HomeScreen.path) {
            HomeScreen(navController,mainViewMoedl)
        }
        composable(DestinationScreen.DetailScreen.path) {
            DetailScreen(navController,mainViewMoedl)
        }
    }

}


sealed class DestinationScreen(val path: String) {
    object SplishScreen : DestinationScreen("SplishScreen")
    object HomeScreen : DestinationScreen("HomeScreen")
    object DetailScreen : DestinationScreen("DetailScreen")
}