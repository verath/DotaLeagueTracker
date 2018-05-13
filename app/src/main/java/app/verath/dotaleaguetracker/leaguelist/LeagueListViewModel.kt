package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.Dota2Service
import app.verath.dotaleaguetracker.DotaLeague
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LeagueListViewModel @Inject constructor(dota2Service: Dota2Service) : ViewModel() {
    private val _leagues = MutableLiveData<List<DotaLeague>>()
    val leagues: LiveData<List<DotaLeague>> = _leagues

    init {
        dota2Service.listLeagues().enqueue(object : Callback<Dota2Service.ListLeaguesResponse> {
            override fun onFailure(call: Call<Dota2Service.ListLeaguesResponse>?, t: Throwable?) {
                TODO("not implemented")
            }

            override fun onResponse(call: Call<Dota2Service.ListLeaguesResponse>?, response: Response<Dota2Service.ListLeaguesResponse>?) {
                response?.body()?.result?.leagues?.let {
                    _leagues.postValue(it)
                }
            }
        })
    }
}
