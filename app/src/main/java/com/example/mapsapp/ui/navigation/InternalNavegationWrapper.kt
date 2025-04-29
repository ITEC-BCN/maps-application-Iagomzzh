package com.example.mapsapp.ui.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mapsapp.ui.navigation.Destination.MarkerCreation
import com.example.mapsapp.ui.screens.CreateMarkerScreen
import com.example.mapsapp.ui.screens.MapsScreen
import com.example.mapsapp.ui.screens.MarkerList
import com.example.mapsapp.viewmodels.PrincipalViewModel


@Composable

fun InternalNavegationWrapperFun(navController: NavHostController, viewModel: PrincipalViewModel) {
    NavHost(navController, Destination.Map) {

        composable<Destination.Map> {
            MapsScreen(){ coordenadasAlt, coordenadasLat -> navController.navigate(MarkerCreation)

            }
        }
        composable<Destination.ListMarker> {
            MarkerList()
        }

        composable<MarkerCreation> { backStackEntry ->
            Log.d("CHIVATOOO01", "Antes del POPI createMarker")

            val popi = backStackEntry.toRoute<MarkerCreation>()
            Log.d("CHIVATOOO01", "Antes del createMarker")
            CreateMarkerScreen(viewModel,popi.lat, popi.alt)
            Log.d("CHIVATOOO01", "Despues del createMarker")

        }
    }

}