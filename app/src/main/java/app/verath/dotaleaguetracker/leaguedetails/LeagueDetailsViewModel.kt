package app.verath.dotaleaguetracker.leaguedetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class LeagueDetailsViewModel @Inject constructor() : ViewModel() {

    private val _leagueId: MutableLiveData<Int> = MutableLiveData()
    val leagueId: LiveData<Int> = _leagueId

    fun setLeagueId(newLeagueId: Int) {
        if (_leagueId.value == newLeagueId) {
            return
        }
        _leagueId.value = newLeagueId
    }
}