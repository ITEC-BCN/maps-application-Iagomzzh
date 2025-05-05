package com.example.mapsapp.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.models.Marcador
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker

class PrincipalViewModel: ViewModel() {


    private val _titleMarker = MutableLiveData<String>()
    val titleMarker = _titleMarker

    private val _descriptionMarker = MutableLiveData<String>()
    val descriptionMarker = _descriptionMarker

    private val _listaMarcadores = MutableLiveData<List<Marcador>>()
    val listaMarcadores = _listaMarcadores


    fun setTitleMarker(title: String){
        _titleMarker.value = title
    }

    fun setDescriptionMarker(description: String){
        _descriptionMarker.value = description
    }



    fun createMarcador(x: Double, y: Double, nombreMarcador: String, descripcionMarcador: String) {
        val nuevoMarcador = Marcador(
            coordenadas = LatLng(x, y),
            nombreMarcador = nombreMarcador,
            descripcionMarcador = descripcionMarcador
        )
        _titleMarker.value = ""
        _descriptionMarker.value = ""
        val listaActual = _listaMarcadores.value.orEmpty()
        _listaMarcadores.value = listaActual + nuevoMarcador
    }









}