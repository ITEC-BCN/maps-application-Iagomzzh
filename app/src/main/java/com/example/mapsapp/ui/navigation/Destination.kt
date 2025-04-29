package com.example.mapsapp.ui.navigation

import kotlinx.serialization.Serializable


sealed class Destination() {
    @Serializable
    object Permissions : Destination()

    @Serializable
    object Drawer : Destination()

    @Serializable
    object Map : Destination()

    @Serializable
    object ListMarker : Destination()

    @Serializable
    data class MarkerCreation(val lat:Double, val alt: Double) : Destination()

}