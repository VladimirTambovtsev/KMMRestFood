package pro.tambovtsev.kmmrestfood.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


const val RECIPE_IMAGE_HEIGHT = 260

@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
){
    Box {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
        )

    }
}