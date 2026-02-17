package com.example.rickandmortycomposepractice.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortycomposepractice.presentation.screens.CharacterDetailScreen
import com.example.rickandmortycomposepractice.presentation.screens.CharacterListScreen

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
    navController = navController,
    startDestination = "list",
    enterTransition = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(500)
        )
    },
    exitTransition = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(500)
        )
    },
    popEnterTransition = {
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(500)
        )
    },
    popExitTransition = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(500)
        )
    }
    ) {
        composable("list") {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("detail/$characterId")
                }
            )
        }

        composable(
            route = "detail/{characterId}",
            arguments = listOf(navArgument("characterId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0

            CharacterDetailScreen(
                characterId = characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}