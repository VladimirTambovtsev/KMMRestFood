package pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import pro.tambovtsev.kmmrestfood.android.presentation.components.RecipeImage
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.components.RecipeCard
import pro.tambovtsev.kmmrestfood.android.presentation.theme.AppTheme
import pro.tambovtsev.kmmrestfood.domain.model.Recipe


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    AppTheme(
        displayProgressBar = false
    ) {
        if(recipe == null){
            Text("Unable to get the details of this recipe...")
        }
        else{
            RecipeCard(recipe = recipe) {}
        }
    }
}