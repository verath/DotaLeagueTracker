package app.verath.dotaleaguetracker.db

import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
interface DatabaseModule {

    @Binds
    fun bindDotaLeagueDao(dotaLeagueDaoDummyImpl: DotaLeagueDaoDummyImpl): DotaLeagueDao
}