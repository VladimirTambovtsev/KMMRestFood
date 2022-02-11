package pro.tambovtsev.kmmrestfood.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import pro.tambovtsev.kmmrestfood.android.presentation.navigation.Navigation
import pro.tambovtsev.kmmrestfood.datasource.network.KtorClientFactory
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeServiceImpl
import pro.tambovtsev.kmmrestfood.datasource.network.model.RecipeDTO
import pro.tambovtsev.kmmrestfood.datasource.network.toRecipe
import pro.tambovtsev.kmmrestfood.domain.util.DatetimeUtil


const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClientFactory = KtorClientFactory().build()

        CoroutineScope(IO).launch {
            val recipeId = 1551
            val recipeService =
                RecipeServiceImpl(httpClient = ktorClientFactory, baseUrl = BASE_URL)
            val recipe = recipeService.get(recipeId)

            print("recipe title: ${recipe.title}")
        }

        setContent {
            Navigation()
        }
    }
}
