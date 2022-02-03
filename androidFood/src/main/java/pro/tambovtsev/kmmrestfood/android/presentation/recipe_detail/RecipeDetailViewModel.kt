package pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pro.tambovtsev.kmmrestfood.android.di.Dummy
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dummy: Dummy,
): ViewModel() {

    val recipeId: MutableState<Int?> = mutableStateOf(null)

    init {
        try {
            savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
                this.recipeId.value = recipeId
            }
        }catch (e: Exception){
            // will throw exception if arg is not there for whatever reason.
            // we don't need to do anything because it will already show a composable saying "Unable to get the details of this recipe..."
            println("Exception: ${e.localizedMessage}")
        }
        println("RecipeDetailViewModel: ${dummy.description()}")
    }
}

