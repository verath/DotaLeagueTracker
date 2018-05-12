package app.verath.dotaleaguetracker.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.DotaLeague
import java.util.*

class LeagueListViewModel : ViewModel() {
    val leagues = MutableLiveData<List<DotaLeague>>()

    init {
        var i = 0
        Timer().schedule(object : TimerTask() {
            override fun run() {
                i++
                val newLeagues = mutableListOf<DotaLeague>()
                for (j in 1..10) {
                    val league = DotaLeague("League $i.$j", i, null, null)
                    newLeagues.add(league)
                }
                leagues.postValue(newLeagues)
            }
        }, 1000, 2000)
    }
}
