package dog.snow.androidrecruittest.core.error

import io.reactivex.Observable
import io.reactivex.functions.Function
import retrofit2.HttpException
import retrofit2.Response

data class RetrofitResponseOrError<T>(
    val response: Response<T>? = null,
    val error: Throwable? = null
) {

    private fun isNonHttpError(): Boolean = error != null

    fun isSuccess(): Boolean = (response != null && response.code() in 200 until 300)

    private fun isHttpErrorResponse(): Boolean = (response != null &&
            response.code() !in 200 until 300)

    fun isAnyError(): Boolean = isNonHttpError() || isHttpErrorResponse()

    companion object {
        fun <T> fromData(data: Response<T>): RetrofitResponseOrError<T> {
            return RetrofitResponseOrError(data, null)
        }

        fun <T> fromError(throwable: Throwable): RetrofitResponseOrError<T> {
            return RetrofitResponseOrError(null, throwable)
        }
    }
}

fun <T> Observable<Response<T>>.toRetrofitResponseOrError(): Observable<RetrofitResponseOrError<T>> {
    return map {
        RetrofitResponseOrError.fromData(it)
    }.onErrorResumeNext(
        Function {
            Observable.just(RetrofitResponseOrError.fromError(it))
        })
}

fun <T> Observable<RetrofitResponseOrError<T>>.onlyError(): Observable<Throwable> {
    return filter {
        it.isAnyError()
    }.map {
        it.error ?: HttpException(it.response!!)
    }
}

fun Observable<Throwable>.mapErrorChain(errors: List<CoreError>): Observable<CoreError> {
    return map { throwable ->
        errors.find { error ->
            error.detect(throwable)
        }
    }
}

fun <T> Observable<RetrofitResponseOrError<T>>.onlySuccess(): Observable<T> {
    return filter {
        it.isSuccess()
    }.map {
        it.response?.body()!!
    }
}
