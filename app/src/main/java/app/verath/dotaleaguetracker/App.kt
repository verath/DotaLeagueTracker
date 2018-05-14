package app.verath.dotaleaguetracker

import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class DotaLeagueTrackerApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        // Enable logging in debug
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}
