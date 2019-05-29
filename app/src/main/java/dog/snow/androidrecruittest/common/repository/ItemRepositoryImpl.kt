package dog.snow.androidrecruittest.common.repository

import dog.snow.androidrecruittest.common.api.ItemApi
import dog.snow.androidrecruittest.common.database.ItemDatabase
import dog.snow.androidrecruittest.common.model.Item
import dog.snow.androidrecruittest.core.error.RetrofitResponseOrError
import io.reactivex.Observable
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
        private val apiDao: ItemApi,
        private val database: ItemDatabase
) : ItemRepository {

    override fun updateItemsObservable(): Observable<RetrofitResponseOrError<List<Item>>> {
        return apiDao.getItems()
                .doAfterNext {
                    if (it.isSuccess()) {
                        database.replaceItems(it.response?.body()!!)
                    }
                    //TODO can clear db here if needed
                }
    }

    override fun getItemsObservable() = database.getItems()
}