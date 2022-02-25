package pro.tambovtsev.kmmrestfood.di

import pro.tambovtsev.kmmrestfood.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val cacheModule: CacheModule
) {
    val getRecipe: GetRecipe by lazy {
        GetRecipe(recipeCache = cacheModule.recipeCache)
    }
}
