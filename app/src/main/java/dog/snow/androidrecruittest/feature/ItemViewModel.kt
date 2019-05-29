package dog.snow.androidrecruittest.feature

import dog.snow.androidrecruittest.common.model.Item
import dog.snow.androidrecruittest.common.repository.ItemRepository
import dog.snow.androidrecruittest.core.error.*
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject

class ItemViewModel @Inject constructor(
        repository: ItemRepository,
        textObservable: Observable<String>,
        refreshObservable: Observable<Unit>
) {

    private val caseInsensitiveTextObservable: Observable<String> =
            textObservable.map {
                it.toLowerCase()
            }

    private val itemsObservable: Observable<MutableList<ListItem>> =
            repository.getItemsObservable().toObservable()
                    .map {
                        val listItems = mutableListOf<ListItem>()
                        it.forEach {
                            listItems.add(ListItem(it))
                        }
                        listItems
                    }

    private val updateItemsObservable: Observable<RetrofitResponseOrError<List<Item>>> =
            refreshObservable
                    .startWith(Unit)
                    .flatMap {
                        repository.updateItemsObservable()
                    }
                    .publish()
                    .refCount()

    val updateItemsSuccessObservable: Observable<Unit> = updateItemsObservable
            .onlySuccess()
            .map {
                Unit
            }

    val updateItemsErrorObservable: Observable<CoreError> = updateItemsObservable
            .onlyError()
            .mapErrorChain(
                    listOf(
                            NoInternetError(),
                            UnknownCoreError()
                    )
            )

    val filteredItemsObservable: Observable<List<ListItem>> = Observables.combineLatest(
            itemsObservable,
            caseInsensitiveTextObservable
    ) { items, searchText ->
        items.filter {
            (it.item.name ?: "")
                    .toLowerCase()
                    .contains(searchText)
        }
    }
}