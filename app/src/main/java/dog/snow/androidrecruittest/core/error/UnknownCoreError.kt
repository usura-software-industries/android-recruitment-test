package dog.snow.androidrecruittest.core.error

import dog.snow.androidrecruittest.R

class UnknownCoreError : CoreError {

    override fun detect(throwable: Throwable) = true
    override fun errorStringRes() = R.string.common_unknown_error
}