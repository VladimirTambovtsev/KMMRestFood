package pro.tambovtsev.kmmrestfood.datasource.cache

import pro.tambovtsev.kmmrestfood.domain.model.Recipe

interface RecipeCache {

    fun insert(recipe: Recipe)

    fun insert(recipes: List<Recipe>)

    fun search(query: String, page: Int): List<Recipe>

    fun getAll(page: Int): List<Recipe>

    fun get(recipeId: Int): Recipe?
}
