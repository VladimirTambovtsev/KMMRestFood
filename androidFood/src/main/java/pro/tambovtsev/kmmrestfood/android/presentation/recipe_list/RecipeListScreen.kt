package pro.tambovtsev.kmmrestfood.android.presentation.recipe_list

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.components.RecipeList
import pro.tambovtsev.kmmrestfood.android.presentation.theme.AppTheme
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListState


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onSelectRecipe: (Int) -> Unit,
){
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onSelectRecipe
        )
    }
}
