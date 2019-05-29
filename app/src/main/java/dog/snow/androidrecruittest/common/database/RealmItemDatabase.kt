package dog.snow.androidrecruittest.common.database

import dog.snow.androidrecruittest.common.model.Item
import io.reactivex.Flowable
import io.realm.Realm

class RealmItemDatabase : ItemDatabase {

    override fun getItems(): Flowable<List<Item>> {
        return Realm.getDefaultInstance()
                .where(RealmItem::class.java)
                .findAllAsync()
                .asFlowable()
                .filter {
                    it.isLoaded && it.isValid && it.isNotEmpty()
                }
                .map { realmResult ->
                    realmResult.map {
                        it.toModel()
                    }
                }
    }

    override fun replaceItems(items: List<Item>) {
        Realm.getDefaultInstance().executeTransactionAsync {
            it.delete(RealmItem::class.java)
            it.copyToRealm(items.map { item ->
                item.toDB()
            })
        }
    }
}