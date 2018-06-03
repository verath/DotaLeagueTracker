package app.verath.dotaleaguetracker.ui.leaguelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.MainThread
import app.verath.dotaleaguetracker.model.DotaLeague
import app.verath.dotaleaguetracker.model.Resource
import app.verath.dotaleaguetracker.repository.DotaLeagueRepository
import javax.inject.Inject

class LeagueListViewModel @Inject constructor(leagueRepository: DotaLeagueRepository) : ViewModel() {
    private val nameFilter = MutableLiveData<String>()

    private val _leagues = MediatorLiveData<Resource<List<DotaLeague>>>().apply {
        val nameFilterSource = nameFilter as LiveData<String>
        val leaguesSource = leagueRepository.loadLeagues()

        @MainThread
        fun filterLeaguesByName() {
            val res = leaguesSource.value
            if (res != null) {
                val name = nameFilterSource.value.orEmpty().toLowerCase()
                val filteredData = res.data?.filter {
                    it.name.toLowerCase().contains(name)
                }
                this.value = Resource(res.status, filteredData, res.msg)
            }
        }
        addSource(nameFilterSource, { filterLeaguesByName() })
        addSource(leaguesSource, { filterLeaguesByName() })
    }

    val leagues: LiveData<Resource<List<DotaLeague>>> = _leagues

    fun setNameFilter(newFilter: String) {
        if (nameFilter.value != newFilter) {
            nameFilter.value = newFilter
        }
    }
}
