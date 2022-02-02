package pro.tambovtsev.kmmrestfood.android.presentation.navigation

sealed class Screen(val route: String) {
    object RecipeList: Screen(route = "recipeList")
    object RecipeDetail: Screen(route = "recipeDetail")
}