package com.example.rickandmortycomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortycomposepractice.presentation.screens.CharacterDetailScreen
import com.example.rickandmortycomposepractice.presentation.screens.CharacterListScreen
import com.example.rickandmortycomposepractice.presentation.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: CharacterViewModel = viewModel()

            NavHost(navController = navController, startDestination = "list") {

                composable("list") {
                    CharacterListScreen(
                        viewModel = viewModel,
                        onCharacterClick = { characterId ->
                            navController.navigate("detail/$characterId")
                        }
                    )
                }

                composable(
                    route = "detail/{characterId}",
                    arguments = listOf(navArgument("characterId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0

                    CharacterDetailScreen(
                        characterId = characterId,
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}