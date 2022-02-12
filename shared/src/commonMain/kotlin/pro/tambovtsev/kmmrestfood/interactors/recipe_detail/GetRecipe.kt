package pro.tambovtsev.kmmrestfood.interactors.recipe_detail

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.util.DataState


/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeService: RecipeService, // We will change this to cache later
){
    fun execute(
        recipeId: Int,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            val recipe =  recipeService.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }

}