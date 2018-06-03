package app.verath.dotaleaguetracker

import android.app.Application
import app.verath.dotaleaguetracker.db.DatabaseModule
import app.verath.dotaleaguetracker.ui.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            UiModule::class,
            DatabaseModule::class
        ]
)
@Suppress("unused")
interface AppComponent : AndroidInjector<DotaLeagueTrackerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
