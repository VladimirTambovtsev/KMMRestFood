package pro.tambovtsev.kmmrestfood.datasource.network

import pro.tambovtsev.kmmrestfood.domain.model.Recipe

interface RecipeService {
    suspend fun search(page: Int, query: String): List<Recipe>

    suspend fun get(id: Int): Recipe
}