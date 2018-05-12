package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.Dota2Service
import app.verath.dotaleaguetracker.DotaLeague
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LeagueListViewModel @Inject constructor(dota2Service: Dota2Service) : ViewModel() {
    val leagues = MutableLiveData<List<DotaLeague>>()

    init {
        dota2Service.listLeagues().enqueue(object : Callback<Dota2Service.ListLeaguesResponse> {
            override fun onFailure(call: Call<Dota2Service.ListLeaguesResponse>?, t: Throwable?) {
                TODO("not implemented")
            }

            override fun onResponse(call: Call<Dota2Service.ListLeaguesResponse>?, response: Response<Dota2Service.ListLeaguesResponse>?) {
                response?.body()?.result?.leagues?.let {
                    leagues.postValue(it)
                }
            }
        })
    }
}
