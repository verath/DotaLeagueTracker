package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
interface LeagueListModule {

    @ContributesAndroidInjector
    fun contributeLeaguesListFragment(): LeagueListFragment

    @Binds
    @IntoMap
    @ViewModelKey(LeagueListViewModel::class)
    fun bindLeagueListViewModel(leagueListViewModel: LeagueListViewModel): ViewModel
}
