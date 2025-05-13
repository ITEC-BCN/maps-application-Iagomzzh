package com.example.mapsapp.models

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.Serializable

@Serializable

data class Marcador(
    val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val nombre_marcador: String,
    val descripcion_marcador: String
) {


}