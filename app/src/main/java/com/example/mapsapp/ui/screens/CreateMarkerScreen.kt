package com.example.mapsapp.ui.screens

import android.R.attr.onClick
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.FileProvider
import com.example.mapsapp.viewmodels.PrincipalViewModel
import java.io.File

@Composable
fun CreateMarkerScreen(viewModel: PrincipalViewModel, x: Double, y: Double, navigate: () -> Unit) {
    Log.d("CHIVATOOO01", "Entro en el CreateMarker, ${x}, ${y}")
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && imageUri.value != null) {
                val stream = context.contentResolver.openInputStream(imageUri.value!!)
                bitmap.value = BitmapFactory.decodeStream(stream)
            }
        }

    fun createImageUri(): Uri? {
        val file = File.createTempFile("temp_image_", ".jpg", context.cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    val pickImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri.value = it
                val stream = context.contentResolver.openInputStream(it)
                bitmap.value = BitmapFactory.decodeStream(stream)
            }
        }

    var showDialog by remember { mutableStateOf(false) }


    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false }, title = { Text("Selecciona una opción") },
            text = { Text("¿Quieres tomar una foto o elegir una desde la galería?") },
            confirmButton = {TextButton(onClick = {
                showDialog = false
                val uri = createImageUri()
                imageUri.value = uri
                launcher.launch(uri!!)
            }) { Text("Tomar Foto") }
            },
            dismissButton = {TextButton(onClick = {
                showDialog = false
                pickImageLauncher.launch("image/*")
            }) { Text("Elegir de Galería") }
            }
        )
    }





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


                }
                .clickable {
                    showDialog = true

                })
        Button(
            onClick = {
                viewModel.createMarcador(
                    x,
                    y,
                    titleMarker.value,
                    descriptionMarker.value
                )
                navigate()

            }, modifier = Modifier
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

