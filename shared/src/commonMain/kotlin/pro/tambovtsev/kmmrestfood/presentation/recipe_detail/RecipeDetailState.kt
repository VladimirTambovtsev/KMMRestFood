package pro.tambovtsev.kmmrestfood.presentation.recipe_detail

import pro.tambovtsev.kmmrestfood.domain.model.GenericMessageInfo
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.util.Queue


data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
){
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        recipe = null,
        queue = Queue(mutableListOf()),
    )
}