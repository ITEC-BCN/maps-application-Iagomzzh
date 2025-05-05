package com.example.mapsapp.ui.navigation
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            MapsScreen(viewModel){ coordenadasAlt, coordenadasLat -> navController.navigate(MarkerCreation(coordenadasAlt, coordenadasLat))

            }
        }
        composable<Destination.ListMarker> {
            MarkerList(viewModel)
        }

        composable<MarkerCreation> { backStackEntry ->
            Log.d("CHIVATOOO01", "Antes del POPI createMarker")

            val params = backStackEntry.toRoute<Destination.MarkerCreation>()
            Log.d("CHIVATOOO01", "Antes del createMarker")
            CreateMarkerScreen(viewModel, params.lat, params.alt) { navController.navigate(Destination.Map) }
            Log.d("CHIVATOOO01", "Despues del createMarker")

        }
    }

}