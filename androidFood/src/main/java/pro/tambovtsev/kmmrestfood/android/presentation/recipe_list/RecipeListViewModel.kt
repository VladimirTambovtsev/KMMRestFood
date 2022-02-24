package pro.tambovtsev.kmmrestfood.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pro.tambovtsev.kmmrestfood.domain.model.GenericMessageInfo
import pro.tambovtsev.kmmrestfood.domain.model.PositiveAction
import pro.tambovtsev.kmmrestfood.domain.model.Recipe
import pro.tambovtsev.kmmrestfood.domain.model.UIComponentType
import pro.tambovtsev.kmmrestfood.domain.util.GenericMessageInfoQueueUtil
import pro.tambovtsev.kmmrestfood.domain.util.Queue
import pro.tambovtsev.kmmrestfood.interactors.recipe_list.SearchRecipes
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.FoodCategory
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListEvents
import pro.tambovtsev.kmmrestfood.presentation.recipe_list.RecipeListState
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes,
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            is RecipeListEvents.OnRemoveHeadMessageFromQueue -> {
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

    private fun onSelectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("$message")
                appendToMessageQueue(messageInfoBuilder)
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
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






