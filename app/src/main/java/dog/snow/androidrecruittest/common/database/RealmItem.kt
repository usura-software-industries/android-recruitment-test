package dog.snow.androidrecruittest.common.database

import dog.snow.androidrecruittest.common.model.Item
import io.realm.RealmObject

open class RealmItem(
        var id: Int? = null,
        var name: String? = null,
        var description: String? = null,
        var icon: String? = null,
        var timestamp: String? = null,
        var url: String? = null
) : RealmObject()

// normally would use repopulator instead but for this scope extensions are sufficient
fun Item.toDB(): RealmItem =
        RealmItem(
                id,
                name,
                description,
                icon,
                timestamp,
                url
        )

fun RealmItem.toModel(): Item =
        Item(
                id,
                name,
                description,
                icon,
                timestamp,
                url
        )