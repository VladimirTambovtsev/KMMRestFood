package pro.tambovtsev.kmmrestfood.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pro.tambovtsev.kmmrestfood.domain.model.GenericMessageInfo
import pro.tambovtsev.kmmrestfood.domain.model.UIComponentType
import pro.tambovtsev.kmmrestfood.domain.util.GenericMessageInfoQueueUtil
import pro.tambovtsev.kmmrestfood.domain.util.Queue
import pro.tambovtsev.kmmrestfood.interactors.recipe_detail.GetRecipe
import pro.tambovtsev.kmmrestfood.presentation.recipe_detail.RecipeDetailEvents
import pro.tambovtsev.kmmrestfood.presentation.recipe_detail.RecipeDetailState
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            getRecipe(recipeId = recipeId)
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when (event) {
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
            }
            is RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Something went wrong.")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).collectCommon(viewModelScope) { dataState ->

            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                println("RecipeDetailVM: recipe: $recipe")
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                println("RecipeDetailVM: error: $message")
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("$message")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo.build()
            )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(queue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(queue = queue)
        }catch (e: Exception){
            // nothing to remove, queue is empty
        }
    }
}







