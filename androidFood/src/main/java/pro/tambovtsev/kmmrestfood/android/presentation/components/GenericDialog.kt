package pro.tambovtsev.kmmrestfood.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.tambovtsev.kmmrestfood.domain.model.NegativeAction
import pro.tambovtsev.kmmrestfood.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadMessageFromQueue: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveHeadMessageFromQueue()
        },
        text = {
            if (description != null) {
                Text(text = description, style = MaterialTheme.typography.body1)
            }
        },
        title = {
            Text(text = title, style = MaterialTheme.typography.h3)
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (negativeAction != null) {
                    Button(
                        onClick = {
                            negativeAction.onNegativeAction()
                            onRemoveHeadMessageFromQueue()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = negativeAction.negativeBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }

                if (positiveAction != null) {
                    Button(
                        onClick = {
                            positiveAction.onPositiveAction()
                            onRemoveHeadMessageFromQueue()
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = positiveAction.positiveBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }

            }
        })
}