package pro.tambovtsev.kmmrestfood.interactors.recipe_detail

import kotlinx.coroutines.flow.flow
import pro.tambovtsev.kmmrestfood.datasource.cache.RecipeCache
import pro.tambovtsev.kmmrestfood.domain.model.GenericMessageInfo
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.model.UIComponentType
import pro.tambovtsev.kmmrestfood.domain.util.CommonFlow
import pro.tambovtsev.kmmrestfood.domain.util.DataState
import pro.tambovtsev.kmmrestfood.domain.util.asCommonFlow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(
        recipeId: Int,
    ): CommonFlow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            val recipe = recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(
                DataState.error<Recipe>(
                    message = GenericMessageInfo.Builder()
                        .id("GetRecipe.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                )
            )
        }
    }.asCommonFlow()
}
