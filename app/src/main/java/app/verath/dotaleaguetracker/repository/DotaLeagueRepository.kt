package app.verath.dotaleaguetracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import app.verath.dotaleaguetracker.api.Dota2Service
import app.verath.dotaleaguetracker.db.DotaLeagueDao
import app.verath.dotaleaguetracker.model.ApiErrorResponse
import app.verath.dotaleaguetracker.model.ApiSuccessResponse
import app.verath.dotaleaguetracker.model.DotaLeague
import app.verath.dotaleaguetracker.model.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DotaLeagueRepository @Inject constructor(
        private val dota2Service: Dota2Service,
        private val dotaLeagueDao: DotaLeagueDao
) {
    fun loadLeagues(): LiveData<Resource<List<DotaLeague>>> {
        val result = MediatorLiveData<Resource<List<DotaLeague>>>()
        result.value = Resource.loading(null)

        fun loadFromNetwork() {
            dota2Service.listLeagues().enqueue(ApiResponseCallback { resp ->
                val resource = when (resp) {
                    is ApiSuccessResponse -> Resource.success(resp.body.result.leagues)
                    is ApiErrorResponse -> Resource.error(resp.errorMessage, null)
                }
                result.postValue(resource)
            })
        }

        val dbSource = dotaLeagueDao.loadLeagues()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (data == null || data.isEmpty()) {
                loadFromNetwork()
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }

        return result
    }
}
