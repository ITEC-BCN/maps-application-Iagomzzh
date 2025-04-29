package com.example.mapsapp.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.Marker

class PrincipalViewModel: ViewModel() {


    private val _titleMarker = MutableLiveData<String>()
    val titleMarker = _titleMarker

    private val _descriptionMarker = MutableLiveData<String>()
    val descriptionMarker = _descriptionMarker

    fun setTitleMarker(title: String){
        _titleMarker.value = title
    }

    fun setDescriptionMarker(description: String){
        _descriptionMarker.value = description
    }







@Composable
fun marcador(){

}


}