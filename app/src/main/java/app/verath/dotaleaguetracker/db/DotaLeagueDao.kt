package app.verath.dotaleaguetracker.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import app.verath.dotaleaguetracker.model.DotaLeague
import javax.inject.Inject

interface DotaLeagueDao {
    fun loadLeagues(): LiveData<List<DotaLeague>>
}

// TODO: replace with actual impl
class DotaLeagueDaoDummyImpl @Inject constructor() : DotaLeagueDao {
    private val dummyData = listOf(
            DotaLeague("The International 2013", 1, null, null),
            DotaLeague("The International 2014", 2, null, null),
            DotaLeague("The International 2015", 3, null, null),
            DotaLeague("The International 2016", 4, null, null),
            DotaLeague("The International 2017", 5, null, null),
            DotaLeague("The International 2018", 6, null, null),
            DotaLeague("ESL One Hamburg 2018", 7, null, null),
            DotaLeague("EPICENTER 2017", 8, null, null),
            DotaLeague("EPICENTER XL", 9, null, null)
    )

    override fun loadLeagues(): LiveData<List<DotaLeague>> {
        return MutableLiveData<List<DotaLeague>>().apply {
            value = dummyData
        }
    }

}
