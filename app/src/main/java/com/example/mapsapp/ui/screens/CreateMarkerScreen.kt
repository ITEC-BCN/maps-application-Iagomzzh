package com.example.mapsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mapsapp.viewmodels.PrincipalViewModel

@Composable
fun CreateMarkerScreen(viewModel: PrincipalViewModel, lat: Double, alt: Double) {
    Log.d("CHIVATOOO01", "Entro en el CreateMarker, ${lat}, ${alt}")


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val titleMarker = viewModel.titleMarker.observeAsState("")
        val descriptionMarker = viewModel.descriptionMarker.observeAsState("")

        val (title, description, iconPhoto, buttonAdd) = createRefs()

        // Creamos guia top

        val guideTopTitle = createGuidelineFromTop(0.20f)
        val guideTopDescription = createGuidelineFromTop(0.30f)
        val guideTopIconPhoto = createGuidelineFromTop(0.50f)
        val guideTopButton = createGuidelineFromTop(0.75f)


        OutlinedTextField(
            value = titleMarker.value,
            onValueChange = { viewModel.setTitleMarker(it) },
            modifier = Modifier.constrainAs(title) {
                top.linkTo(guideTopTitle)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            },
            placeholder = { Text("Titulo del marcador", textAlign = TextAlign.Center) }
        )

        OutlinedTextField(
            value = descriptionMarker.value,
            onValueChange = { viewModel.setDescriptionMarker(it) },
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(guideTopDescription)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                .height(100.dp),
            placeholder = { Text("Descripción del marcador", textAlign = TextAlign.Center) },

            )

        Icon(
            imageVector = Icons.Filled.CameraAlt,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .constrainAs(iconPhoto) {
                    top.linkTo(guideTopIconPhoto)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)


                })
        Button(
            onClick = {}, modifier = Modifier
                .constrainAs(buttonAdd) {
                    top.linkTo(guideTopButton)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(
                    shape = RoundedCornerShape(5.dp),
                ), colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            )
        ) {
            Text("Add")
        }
    }
}

