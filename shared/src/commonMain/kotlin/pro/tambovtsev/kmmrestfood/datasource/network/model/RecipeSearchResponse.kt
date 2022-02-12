package pro.tambovtsev.kmmrestfood.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse (
    @SerialName("count")
    var count: Long,

    @SerialName("results")
    var results: List<RecipeDto>
)