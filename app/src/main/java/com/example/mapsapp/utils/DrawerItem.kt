package com.example.mapsapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mapsapp.ui.navigation.Destination

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Destination
) {
    HOME(Icons.Default.Map, "Home", Destination.Map),
    SETTINGS(Icons.Default.FilterList, "Lista de marcadores", Destination.ListMarker)
}
