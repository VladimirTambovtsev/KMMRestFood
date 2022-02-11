package pro.tambovtsev.kmmrestfood.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import pro.tambovtsev.kmmrestfood.android.BASE_URL
import pro.tambovtsev.kmmrestfood.datasource.network.KtorClientFactory
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(httpClient: HttpClient): RecipeService {
        return RecipeServiceImpl(httpClient = httpClient, baseUrl = BASE_URL)
    }
}