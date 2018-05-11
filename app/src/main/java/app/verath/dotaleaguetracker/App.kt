package app.verath.dotaleaguetracker

import android.app.Application
import timber.log.Timber

class DotaLeagueTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Enable logging in debug
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
