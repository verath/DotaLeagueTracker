package app.verath.dotaleaguetracker.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import app.verath.dotaleaguetracker.api.Dota2Service
import app.verath.dotaleaguetracker.model.ApiErrorResponse
import app.verath.dotaleaguetracker.model.ApiSuccessResponse
import app.verath.dotaleaguetracker.model.DotaLeague
import app.verath.dotaleaguetracker.model.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DotaLeagueRepository @Inject constructor(private val dota2Service: Dota2Service) {
    fun loadLeagues(): LiveData<Resource<List<DotaLeague>>> {
        val result = MutableLiveData<Resource<List<DotaLeague>>>()
        result.value = Resource.loading(null)

        dota2Service.listLeagues().enqueue(ApiResponseCallback({
            val resource = when (it) {
                is ApiSuccessResponse -> Resource.success(it.body.result.leagues)
                is ApiErrorResponse -> Resource.error(it.errorMessage, null)
            }
            result.postValue(resource)
        }))

        return result
    }
}
