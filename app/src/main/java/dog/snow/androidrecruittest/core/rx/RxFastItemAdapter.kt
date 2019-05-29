package dog.snow.androidrecruittest.core.rx

import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

typealias FastItem = IItem<*, *>

class RxFastItemAdapter : FastItemAdapter<FastItem>(), Consumer<MutableList<FastItem>> {

    val clickedPositionSubject: PublishSubject<Int> = PublishSubject.create()

    init {
        withOnClickListener { _, _, _, position ->
            clickedPositionSubject.onNext(position)
            true
        }
    }

    override fun accept(newItems: MutableList<FastItem>) {
        setNewList(newItems)
    }
}