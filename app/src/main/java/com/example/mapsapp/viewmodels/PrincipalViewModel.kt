package com.example.mapsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.MyApp
import com.example.mapsapp.MyApp.Companion.database
import com.example.mapsapp.models.Marcador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrincipalViewModel : ViewModel() {
    val mySupabaseClient = MyApp.database


    private val _titleMarker = MutableLiveData<String>()
    val titleMarker = _titleMarker

    private val _descriptionMarker = MutableLiveData<String>()
    val descriptionMarker = _descriptionMarker

    private val _listaMarcadores = MutableLiveData<List<Marcador>>()
    val listaMarcadores = _listaMarcadores


    fun setTitleMarker(title: String) {
        _titleMarker.value = title
    }

    fun setDescriptionMarker(description: String) {
        _descriptionMarker.value = description
    }


    fun createMarcador(x: Double, y: Double, nombreMarcador: String, descripcionMarcador: String) {
        val nuevoMarcador = Marcador(
            latitude = x,
            longitude = y,
            nombre_marcador = nombreMarcador,
            descripcion_marcador = descripcionMarcador
        )
        _titleMarker.value = ""
        _descriptionMarker.value = ""
        val listaActual = _listaMarcadores.value.orEmpty()
        _listaMarcadores.value = listaActual + nuevoMarcador
        insertNewMarker(nuevoMarcador)

    }

    fun getAllMarkers() {
        CoroutineScope(Dispatchers.IO).launch {
            val marcadores = database.getAllMarkers()
            withContext(Dispatchers.Main) {
                _listaMarcadores.value = marcadores
            }
        }
    }


    fun insertNewMarker(marcador: Marcador) {
        CoroutineScope(Dispatchers.IO).launch {
            mySupabaseClient.insertMarker(marcador)
            getAllMarkers()
        }


    }
}



