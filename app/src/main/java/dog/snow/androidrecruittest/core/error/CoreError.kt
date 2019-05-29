package dog.snow.androidrecruittest.core.error

interface CoreError {
    fun detect(throwable: Throwable): Boolean
    fun errorStringRes(): Int
}
