// kotlin - java/com/example/mapsapp/ui/screens/MapScreen.kt
package com.example.mapsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.mapsapp.viewmodels.PrincipalViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    viewModel: PrincipalViewModel,
    modifier: Modifier = Modifier,
    navigateToCreateMarker: (Double, Double) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getAllMarkers()

    }
    val markers = viewModel.listaMarcadores.observeAsState(emptyList())
    val initialPosition = LatLng(41.4534225, 2.1837151)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 17f)
    }

    Column(modifier.fillMaxSize()) {
        GoogleMap(
            modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = { lat ->
                Log.d("CHIVATOOO01", "antes de navegar al createMarker")
                navigateToCreateMarker(lat.latitude, lat.longitude)
            }
        ) {
            markers.value.forEach { marcador ->
                Marker(
                    state = MarkerState( LatLng(marcador.latitude, marcador.longitude)),
                    title = marcador.nombre_marcador,
                    snippet = marcador.descripcion_marcador
                )
            }
        }
    }
}