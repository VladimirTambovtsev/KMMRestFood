package pro.tambovtsev.kmmrestfood.android.presentation.recipe_list

import android.hardware.TriggerEvent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.components.RecipeList
import pro.tambovtsev.kmmrestfood.android.presentation.recipe_list.components.SearchAppBar
import pro.tambovtsev.kmmrestfood.android.presentation.theme.AppTheme
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListEvents
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListState


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onSelectRecipe: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
        Scaffold(topBar = {
            SearchAppBar(
                query = state.query,
                onQueryChanged = {
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                },
            )
        }) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = { onTriggerEvent(RecipeListEvents.NextPage) },
                onClickRecipeListItem = onSelectRecipe
            )
        }
    }
}
