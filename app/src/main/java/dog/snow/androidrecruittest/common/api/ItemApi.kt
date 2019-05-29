package dog.snow.androidrecruittest.common.api

import dog.snow.androidrecruittest.common.model.Item
import dog.snow.androidrecruittest.core.error.RetrofitResponseOrError
import io.reactivex.Observable

interface ItemApi {

    fun getItems(): Observable<RetrofitResponseOrError<List<Item>>>
}