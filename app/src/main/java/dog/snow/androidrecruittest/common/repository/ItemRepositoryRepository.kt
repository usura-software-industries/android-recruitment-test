package dog.snow.androidrecruittest.common.repository

import dog.snow.androidrecruittest.common.model.Item
import dog.snow.androidrecruittest.core.error.RetrofitResponseOrError
import io.reactivex.Flowable
import io.reactivex.Observable

interface ItemRepositoryRepository {

    fun updateItemsObservable(): Observable<RetrofitResponseOrError<List<Item>>>
    fun getItemsObservable(): Flowable<List<Item>>
}