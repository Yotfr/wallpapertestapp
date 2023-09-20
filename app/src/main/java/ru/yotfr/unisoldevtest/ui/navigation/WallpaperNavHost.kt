package ru.yotfr.unisoldevtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categories.screen.CategoriesScreen

@Composable
fun WallpaperNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Categories.route
    ) {
        composable(
            route = Screen.Categories.route
        ) {
            CategoriesScreen(
                navigateToCategoryWallpaper = { category ->
                    navController.navigate(Screen.CategoryWallpapers.passCategory(category))
                }
            )
        }
        composable(
            route = Screen.CategoryWallpapers.route,
            arguments = listOf(
                navArgument(NavigationConstants.CATEGORY_KEY) {}
            )
        ) { backStackEntry ->
            val category = Category.valueOf(
                backStackEntry.arguments?.getString(
                    NavigationConstants.CATEGORY_KEY
                ) ?: throw IllegalArgumentException("Navigated with wrong Category")
            )
        }
    }
}