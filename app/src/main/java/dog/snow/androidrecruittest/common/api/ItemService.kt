package dog.snow.androidrecruittest.common.api

import dog.snow.androidrecruittest.common.model.Item
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ItemService {

    @GET("items")
    fun getItems(): Observable<Response<List<Item>>>
}