package dog.snow.androidrecruittest.feature

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.core.BaseActivity
import dog.snow.androidrecruittest.core.rx.RxFastItemAdapter
import dog.snow.androidrecruittest.core.rx.toast
import dog.snow.androidrecruittest.feature.MainActivity.Companion.DEBOUNCE_MS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        const val DEBOUNCE_MS = 500L
    }

    override fun getLayoutId() = R.layout.activity_main

    @Inject
    lateinit var viewModel: ItemViewModel

    private val adapter = RxFastItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupList()

        subscriptions.addAll(
                viewModel.updateItemsSuccessObservable.subscribe {
                    refresh_layout.isRefreshing = false
                },
                viewModel.updateItemsErrorObservable.subscribe {
                    refresh_layout.isRefreshing = false
                    toast(getString(it.errorStringRes()))
                },
                viewModel.filteredItemsObservable.subscribe {
                    adapter.setNewList(it)
                    if (it.isEmpty()) {
                        empty_list_tv.visibility = View.VISIBLE
                        items_rv.visibility = View.GONE
                        search_et.visibility = View.GONE
                    } else {
                        empty_list_tv.visibility = View.GONE
                        items_rv.visibility = View.VISIBLE
                        search_et.visibility = View.VISIBLE
                    }
                }
        )
    }

    private fun setupList() {
        items_rv.adapter = adapter
        items_rv.layoutManager = LinearLayoutManager(this)
    }
}

@Module
class MainActivityModule {

    @Provides
    fun providesSearchText(activity: MainActivity): Observable<String> =
            activity.search_et.afterTextChangeEvents()
                    .map {
                        it.editable.toString()
                    }
                    .debounce(DEBOUNCE_MS, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())

    @Provides
    fun providesRefresh(activity: MainActivity): Observable<Unit> =
            activity.refresh_layout.refreshes()
}
