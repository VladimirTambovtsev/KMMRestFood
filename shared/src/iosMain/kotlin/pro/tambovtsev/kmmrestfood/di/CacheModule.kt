package pro.tambovtsev.kmmrestfood.di

import pro.tambovtsev.kmmrestfood.datasource.cache.*
import pro.tambovtsev.kmmrestfood.domain.util.DatetimeUtil

class CacheModule {
    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(recipeDatabase = recipeDatabase, datetimeUtil = DatetimeUtil())
    }
}
