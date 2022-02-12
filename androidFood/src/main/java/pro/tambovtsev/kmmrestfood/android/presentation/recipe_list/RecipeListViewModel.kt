package pro.tambovtsev.kmmrestfood.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.interactors.recipe_list.SearchRecipes
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListState
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes,
): ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            println("RecipeListVM: ${dataState.isLoading}")

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                println("RecipeListVM: recipes: ${recipes}")
                appendRecipes(recipes)
                state.value = state.value.copy(recipes = recipes)
            }

            dataState.message?.let { message ->
                println("RecipeListVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(state.value.recipes)
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }
}






