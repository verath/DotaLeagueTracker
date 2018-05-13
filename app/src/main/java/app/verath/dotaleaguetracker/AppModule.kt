package app.verath.dotaleaguetracker

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import app.verath.dotaleaguetracker.api.Dota2Service
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDota2Service(app: Application): Dota2Service {
        return Dota2Service.create(app.getString(R.string.STEAM_API_KEY))
    }

    @Provides
    @Singleton
    fun providesAppViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory {
        return appViewModelFactory
    }
}
