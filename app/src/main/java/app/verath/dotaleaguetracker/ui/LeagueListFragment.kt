package app.verath.dotaleaguetracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.verath.dotaleaguetracker.databinding.FragmentLeagueListBinding


class LeagueListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LeagueListFragment()
    }

    private lateinit var viewModel: LeagueListViewModel

    private lateinit var leagueListAdapter: LeagueListAdapter
    private lateinit var leagueListLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeagueListViewModel::class.java)
        leagueListAdapter = LeagueListAdapter()

        viewModel.leagues.observe(this, Observer {
            it?.run {
                leagueListAdapter.setLeagues(it)
                leagueListAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLeagueListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        leagueListLayoutManager = LinearLayoutManager(context)
        binding.leagueList.apply {
            layoutManager = leagueListLayoutManager
            adapter = leagueListAdapter
        }
        return binding.root
    }
}

