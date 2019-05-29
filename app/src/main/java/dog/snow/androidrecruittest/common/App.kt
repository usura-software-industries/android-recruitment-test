package dog.snow.androidrecruittest.common

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.common.injection.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

import timber.log.Timber
import timber.log.Timber.DebugTree

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        setupRealm()
        setupTimber()
    }

    private fun setupRealm() {
        Realm.init(this)
        val config = RealmConfiguration
            .Builder()
            .name("android_test.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}
