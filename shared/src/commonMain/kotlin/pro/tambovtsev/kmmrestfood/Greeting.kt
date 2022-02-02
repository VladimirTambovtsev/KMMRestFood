package pro.tambovtsev.kmmrestfood

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}