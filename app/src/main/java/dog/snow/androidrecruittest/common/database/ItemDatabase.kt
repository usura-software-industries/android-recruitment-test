package dog.snow.androidrecruittest.common.database

import dog.snow.androidrecruittest.common.model.Item
import io.reactivex.Flowable

interface ItemDatabase {

    fun getItems(): Flowable<List<Item>>
    fun replaceItems(items: List<Item>)
}