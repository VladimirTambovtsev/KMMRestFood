package pro.tambovtsev.kmmrestfood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.interactors.recipe_detail.GetRecipe
import pro.tambovtsev.kmmrestfood.interactors.recipe_list.SearchRecipes
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {
    @Singleton
    @Provides
    fun searchRecipes(recipeService: RecipeService): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }

    @Singleton
    @Provides
    fun provideGetRecipe(recipeService: RecipeService): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}