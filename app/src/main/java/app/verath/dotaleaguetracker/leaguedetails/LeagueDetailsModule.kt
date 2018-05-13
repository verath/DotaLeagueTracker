package app.verath.dotaleaguetracker.leaguedetails

import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
interface LeagueDetailsModule {

    @ContributesAndroidInjector
    fun contributeLeaguesDetailsFragment(): LeagueDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(LeagueDetailsViewModel::class)
    fun bindLeagueListViewModel(leagueDetailsViewModel: LeagueDetailsViewModel): ViewModel
}
