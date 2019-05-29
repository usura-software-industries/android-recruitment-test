package dog.snow.androidrecruittest.common.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dog.snow.androidrecruittest.feature.MainActivity
import dog.snow.androidrecruittest.feature.MainActivityModule

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributesMainActivity(): MainActivity
}
