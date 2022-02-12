package pro.tambovtsev.kmmrestfood.android.presentation.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Center a circular indeterminate progress bar with optional vertical bias.
 *
 * NOTE: You do not need a ConstraintLayout here. A Row would have been perfectly fine.
 * I just left it here as an example.
 */
@Composable
fun CircularProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if (isDisplayed) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}