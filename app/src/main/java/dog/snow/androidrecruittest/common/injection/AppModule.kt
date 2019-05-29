package dog.snow.androidrecruittest.common.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Provides
    @Singleton
    @Named("ui")
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
