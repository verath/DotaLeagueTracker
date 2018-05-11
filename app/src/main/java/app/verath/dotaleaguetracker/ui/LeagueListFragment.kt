package app.verath.dotaleaguetracker.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.verath.dotaleaguetracker.R
import kotlinx.android.synthetic.main.fragment_league_list.view.*


class LeagueListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LeagueListFragment()
    }

    private lateinit var leagueListAdapter: LeagueListAdapter
    private lateinit var leagueListLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_league_list, container, false)
        //view.btnToDetails.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_league_details))

        leagueListAdapter = LeagueListAdapter()
        leagueListLayoutManager = LinearLayoutManager(context)
        view.league_list.apply {
            layoutManager = leagueListLayoutManager
            adapter = leagueListAdapter
        }
        return view
    }
}

