package pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail

import android.hardware.TriggerEvent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.tambovtsev.kmmrestfood.android.presentation.components.RecipeImage
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail.components.RecipeView
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.components.RecipeCard
import pro.tambovtsev.kmmrestfood.android.presentation.theme.AppTheme
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.presentation.recipe_detail.RecipeDetailEvents
import pro.tambovtsev.kmmrestfood.presentation.recipe_detail.RecipeDetailState


@ExperimentalStdlibApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit
) {
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
        if (state.recipe == null && state.isLoading) {

        } else if (state.recipe == null) {
            Text(
                "Unable to get the details of this recipe... ",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )
        } else {
            RecipeView(recipe = state.recipe!!)
        }
    }
}