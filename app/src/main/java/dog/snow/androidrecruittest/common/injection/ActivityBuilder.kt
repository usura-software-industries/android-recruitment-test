package dog.snow.androidrecruittest.common.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.MainActivityModule

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributesMainActivity(): MainActivity
}
