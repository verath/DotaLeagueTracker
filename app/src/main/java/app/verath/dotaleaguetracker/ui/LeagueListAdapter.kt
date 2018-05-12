package app.verath.dotaleaguetracker.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.verath.dotaleaguetracker.DotaLeague
import app.verath.dotaleaguetracker.databinding.ItemLeagueBinding

class LeagueListAdapter : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {

    private var leagues: List<DotaLeague> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLeagueBinding = ItemLeagueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemLeagueBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.leagues[position])
    }

    override fun getItemCount() = this.leagues.size

    fun setLeagues(leagues: List<DotaLeague>) {
        this.leagues = leagues
    }

    class ViewHolder(private val itemLeagueBinding: ItemLeagueBinding) : RecyclerView.ViewHolder(itemLeagueBinding.root) {
        fun bind(dotaLeague: DotaLeague) {
            itemLeagueBinding.dotaLeague = dotaLeague
            itemLeagueBinding.executePendingBindings()
        }
    }
}
