package pro.tambovtsev.kmmrestfood.di

import pro.tambovtsev.kmmrestfood.datasource.network.KtorClientFactory
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeServiceImpl

class NetworkModule {
    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }
}