package app.verath.dotaleaguetracker.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.verath.dotaleaguetracker.R

class LeagueListAdapter : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_league, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.name)
        nameTextView.text = String.format("Item %d", position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
