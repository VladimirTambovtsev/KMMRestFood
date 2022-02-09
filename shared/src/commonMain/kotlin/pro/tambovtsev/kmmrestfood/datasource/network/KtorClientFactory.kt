package pro.tambovtsev.kmmrestfood.datasource.network


import io.ktor.client.*
import pro.tambovtsev.kmmrestfood.datasource.network.model.RecipeDTO
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.util.DatetimeUtil

expect class KtorClientFactory() {
    fun build(): HttpClient
}

fun RecipeDTO.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredientsList = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDTO>.toRecipeList(): List<Recipe> {
    return map{ it.toRecipe() }
}