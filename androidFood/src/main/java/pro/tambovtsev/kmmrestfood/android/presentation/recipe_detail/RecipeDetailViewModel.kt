package pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.tambovtsev.kmmrestfood.datasource.network.RecipeService
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.interactors.recipe_detail.GetRecipe
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
): ViewModel() {

    val recipe: MutableState<Recipe?> = mutableStateOf(null)

    init {
        try {
            savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
                viewModelScope.launch {
                    getRecipe(recipeId = recipeId)
                }
            }
        }catch (e: Exception){
            // will throw exception if arg is not there for whatever reason.
            // we don't need to do anything because it will already show a composable saying "Unable to get the details of this recipe..."
            println("Exception: ${e.localizedMessage}")
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            println("RecipeDetailVM: ${dataState.isLoading}")
            dataState.data?.let { recipe ->
                print("RecipeDetailVM ${recipe}")
                this.recipe.value = recipe
            }
            dataState.message?.let { message ->
                print("RecipeDetailVM ${recipe}")
            }
        }.launchIn(viewModelScope)
    }
}

