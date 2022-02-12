package pro.tambovtsev.kmmrestfood.interactors.recipe_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.util.DataState

class SearchRecipes(
    private val recipeService: RecipeService,
) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page = page, query = query)
            emit(DataState.data(message = null, data = recipes))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}