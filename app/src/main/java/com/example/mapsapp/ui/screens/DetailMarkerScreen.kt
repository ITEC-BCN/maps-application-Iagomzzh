package com.example.mapsapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DetailMarkerScreen() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (tituloPagina, flechaIrParaAtras, nombreMarcador, descripcionMarcador, fotoMarcador) = createRefs()
        val createTopGuide = createGuidelineFromTop(0.1f)
        val createTopGuideForArrowBack = createGuidelineFromTop(0.06f)

        Text(
            "Detalles del Marcador",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(tituloPagina) {

                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(createTopGuide)


            })

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(flechaIrParaAtras) {

                    start.linkTo(parent.start)
                    end.linkTo(tituloPagina.start)
                    top.linkTo(createTopGuideForArrowBack)
                }
                .size(30.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(nombreMarcador) {

                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(tituloPagina.bottom, margin = 45.dp)
            })


        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(descripcionMarcador) {

                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(nombreMarcador.bottom, margin = 45.dp)
            })

        Icon(
            imageVector = Icons.Filled.CameraAlt,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .constrainAs(fotoMarcador) {
                    top.linkTo(descripcionMarcador.bottom, margin = 45.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)


                })


    }


}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Previeww() {
    DetailMarkerScreen()

}