package dog.snow.androidrecruittest.core.error

import dog.snow.androidrecruittest.R
import java.io.IOException

class NoInternetError : CoreError {

    override fun detect(throwable: Throwable) = throwable is IOException
    override fun errorStringRes() = R.string.common_no_internet_error
}