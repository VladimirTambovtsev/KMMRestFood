package pro.tambovtsev.kmmrestfood.domain.model

sealed class UIComponentType {
    object Dialog: UIComponentType()
    object None: UIComponentType()
}
