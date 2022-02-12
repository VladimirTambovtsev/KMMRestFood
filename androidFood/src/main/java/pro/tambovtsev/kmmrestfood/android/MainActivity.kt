package pro.tambovtsev.kmmrestfood.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import pro.tambovtsev.kmmrestfood.android.presentation.navigation.Navigation


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(
        ExperimentalStdlibApi::class,
        ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}