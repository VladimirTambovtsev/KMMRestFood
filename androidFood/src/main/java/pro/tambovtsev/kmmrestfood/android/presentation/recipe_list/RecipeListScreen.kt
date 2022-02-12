package pro.tambovtsev.kmmrestfood.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pro.tambovtsev.kmmrestfood.android.presentation.navigation.Screen
import pro.tambovtsev.kmmrestfood.android.presentation.theme.AppTheme


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    onSelectRecipe: (Int) -> Unit,
){
    AppTheme(
        displayProgressBar = false
    ) {
        LazyColumn {
            items(100){ recipeId ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelectRecipe(recipeId)
                        }
                ){
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.h2,
                        text = "RecipeId = ${recipeId}"
                    )
                }
            }
        }
    }
}
