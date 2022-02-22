package pro.tambovtsev.kmmrestfood.presentation.recipe_detail

import pro.tambovtsev.kmmrestfood.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)
