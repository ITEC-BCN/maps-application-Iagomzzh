// kotlin - java/com/example/mapsapp/ui/screens/MarkerListScreen.kt
package com.example.mapsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mapsapp.utils.PermissionStatus
import com.example.mapsapp.viewmodels.PrincipalViewModel

@Composable
fun MarkerList(viewModel: PrincipalViewModel) {
    LaunchedEffect(Unit) {
       viewModel.getAllMarkers()

    }
    val markers = viewModel.listaMarcadores.observeAsState(emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(markers.value) { marker ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    Text(
                        text = "Nombre: ${marker.nombre_marcador}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Descripción: ${marker.descripcion_marcador}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}