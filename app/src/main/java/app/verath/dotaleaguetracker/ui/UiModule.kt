package app.verath.dotaleaguetracker.ui

import app.verath.dotaleaguetracker.ui.leaguedetails.LeagueDetailsModule
import app.verath.dotaleaguetracker.ui.leaguelist.LeagueListModule
import dagger.Module

@Module(includes = [
    LeagueListModule::class,
    LeagueDetailsModule::class
])
class UiModule
