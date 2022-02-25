package pro.tambovtsev.kmmrestfood.interactors.recipe_list

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import pro.tambovtsev.kmmrestfood.datasource.cache.RecipeCache
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.domain.model.GenericMessageInfo
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.model.UIComponentType
import pro.tambovtsev.kmmrestfood.domain.util.CommonFlow
import pro.tambovtsev.kmmrestfood.domain.util.DataState
import pro.tambovtsev.kmmrestfood.domain.util.asCommonFlow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(
                page = page,
                query = query,
            )

            // delay 500ms so we can see loading
            delay(500)

            if (query == "error") {
                throw Exception("Forcing an error; search failed")
            }

            // insert into cache
            recipeCache.insert(recipes)

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(
                DataState.error<List<Recipe>>(
                    message = GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                )
            )
        }
    }.asCommonFlow()
}
