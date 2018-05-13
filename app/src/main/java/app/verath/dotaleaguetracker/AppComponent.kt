package app.verath.dotaleaguetracker

import android.app.Application
import app.verath.dotaleaguetracker.leaguelist.LeagueListModule
import app.verath.dotaleaguetracker.leaguedetails.LeagueDetailsModule
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
            LeagueListModule::class,
            LeagueDetailsModule::class
        ]
)
interface AppComponent : AndroidInjector<DotaLeagueTrackerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
