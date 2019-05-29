package dog.snow.androidrecruittest.common.api

import dog.snow.androidrecruittest.common.model.Item
import dog.snow.androidrecruittest.core.error.RetrofitResponseOrError
import dog.snow.androidrecruittest.core.error.toRetrofitResponseOrError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class RetrofitItemApi(retrofit: Retrofit) : ItemApi {

    private val itemService = retrofit.create(ItemService::class.java)

    override fun getItems(): Observable<RetrofitResponseOrError<List<Item>>> {
        return itemService.getItems()
            .toRetrofitResponseOrError()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}