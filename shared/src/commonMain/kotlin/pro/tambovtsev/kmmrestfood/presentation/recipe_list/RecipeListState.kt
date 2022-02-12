package pro.tambovtsev.kmmrestfood.presentation.recipe_list

import pro.tambovtsev.kmmrestfood.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf()
) {}