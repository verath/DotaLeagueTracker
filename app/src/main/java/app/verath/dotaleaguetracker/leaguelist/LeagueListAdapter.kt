package app.verath.dotaleaguetracker.leaguelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.verath.dotaleaguetracker.DotaLeague
import app.verath.dotaleaguetracker.databinding.ItemLeagueBinding

typealias LeagueClickedListener = ((DotaLeague) -> Unit)

class LeagueListAdapter(
        private val onLeagueClickedListener: LeagueClickedListener? = null
) : RecyclerView.Adapter<LeagueListViewHolder>() {

    private var leagues: List<DotaLeague> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLeagueBinding = ItemLeagueBinding.inflate(layoutInflater, parent, false)
        return LeagueListViewHolder(itemLeagueBinding, onLeagueClickedListener)
    }

    override fun onBindViewHolder(holder: LeagueListViewHolder, position: Int) {
        holder.bind(this.leagues[position])
    }

    override fun getItemCount() = this.leagues.size

    fun setLeagues(leagues: List<DotaLeague>) {
        this.leagues = leagues
    }

}

class LeagueListViewHolder(
        private val itemLeagueBinding: ItemLeagueBinding,
        onLeagueClickedListener: LeagueClickedListener?
) : RecyclerView.ViewHolder(itemLeagueBinding.root) {

    init {
        itemLeagueBinding.root.setOnClickListener {
            itemLeagueBinding.dotaLeague?.let {
                onLeagueClickedListener?.invoke(it)
            }
        }
    }

    fun bind(dotaLeague: DotaLeague) {
        itemLeagueBinding.dotaLeague = dotaLeague
        itemLeagueBinding.executePendingBindings()
    }
}
