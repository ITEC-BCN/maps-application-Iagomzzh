package com.example.mapsapp.ui.navigation

import DraweScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapsapp.ui.screens.PermissionsScreen

@Composable

fun MainNavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController, Destination.Permissions) {

        composable<Destination.Permissions> {
            PermissionsScreen{
                navController.navigate(Destination.Drawer)
            }
        }
        composable<Destination.Drawer> {
            DraweScreen()
        }
    }
}