package pro.tambovtsev.kmmrestfood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.tambovtsev.kmmrestfood.datasource.cache.RecipeCache
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.interactors.recipe_detail.GetRecipe
import pro.tambovtsev.kmmrestfood.interactors.recipe_list.SearchRecipes
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    @Singleton
    @Provides
    fun searchRecipes(recipeService: RecipeService, recipeCache: RecipeCache): SearchRecipes {
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(recipeCache: RecipeCache): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }
}