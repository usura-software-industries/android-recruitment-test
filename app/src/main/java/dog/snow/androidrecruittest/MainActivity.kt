package dog.snow.androidrecruittest

import android.os.Bundle
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.core.BaseActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Module
class MainActivityModule {

    @Provides
    fun providesSearchText(activity: MainActivity): Observable<String> =
            RxTextView.afterTextChangeEvents(activity.search_et)
                    .map {
                        it.editable()!!.toString()
                    }
}
