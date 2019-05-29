package dog.snow.androidrecruittest.common.injection

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributesFragmentWorkaround(): Fragment
}
