package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import app.verath.dotaleaguetracker.model.DotaLeague
import app.verath.dotaleaguetracker.model.Resource
import app.verath.dotaleaguetracker.repository.DotaLeagueRepository
import javax.inject.Inject

class LeagueListViewModel @Inject constructor(leagueRepository: DotaLeagueRepository) : ViewModel() {
    val leagues: LiveData<Resource<List<DotaLeague>>> = leagueRepository.loadLeagues()
}
