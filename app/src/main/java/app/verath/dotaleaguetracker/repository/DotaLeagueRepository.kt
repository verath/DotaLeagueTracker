package app.verath.dotaleaguetracker.repository

import android.arch.lifecycle.LiveData
import app.verath.dotaleaguetracker.AppExecutors
import app.verath.dotaleaguetracker.api.Dota2Service
import app.verath.dotaleaguetracker.api.ListLeaguesResponse
import app.verath.dotaleaguetracker.db.DotaLeagueDao
import app.verath.dotaleaguetracker.model.DotaLeague
import app.verath.dotaleaguetracker.model.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DotaLeagueRepository @Inject constructor(
        private val dota2Service: Dota2Service,
        private val dotaLeagueDao: DotaLeagueDao,
        private val appExecutors: AppExecutors
) {

    fun loadLeagues(): LiveData<Resource<List<DotaLeague>>> {
        return object : NetworkBoundResource<List<DotaLeague>, ListLeaguesResponse>(appExecutors) {

            override fun loadFromDb() = dotaLeagueDao.loadLeagues()

            override fun shouldFetch(data: List<DotaLeague>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall() = dota2Service.listLeagues()

            override fun saveCallResult(item: ListLeaguesResponse) {
                //TODO: dotaLeagueDao.insertLeagues(item.result.leagues)
            }

        }.asLiveData()
    }
}
