package pro.tambovtsev.kmmrestfood.android.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail.RecipeDetailScreen
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail.RecipeDetailViewModel
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.RecipeListScreen
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.RecipeListViewModel

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalStdlibApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            // in the future, the hilt-navigation-compose artifact will simplify this
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel =
                viewModel(key = "RecipeListViewModel", factory = factory)
            RecipeListScreen(
                onSelectRecipe = { recipeId ->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeDetailViewModel =
                viewModel(key = "RecipeDetailViewModel", factory = factory)

            print("viewModel.recipe.value: ${viewModel.recipe.value}")

            RecipeDetailScreen(
                recipe = viewModel.recipe.value
            )
        }
    }
}

//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
//        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
//            // in the future, the hilt-navigation-compose artifact will simplify this
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeListViewModel =
//                viewModel(key = "RecipeListViewModel", factory = factory)
//            RecipeListScreen(
//                onSelectRecipe = { recipeId ->
//                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
//                }
//            )
//        }
//        composable(
//            route = Screen.RecipeDetail.route + "/{recipeId}",
//            arguments = listOf(navArgument("recipeId") {
//                type = NavType.IntType
//            })
//        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeDetailViewModel =
//                viewModel(key = "RecipeDetailViewModel", factory = factory)
//            RecipeDetailScreen(
//                recipe = viewModel.recipe.value
//            )
//        }
//    }
//}