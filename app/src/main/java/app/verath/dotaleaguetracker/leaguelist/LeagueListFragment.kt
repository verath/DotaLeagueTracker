package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.verath.dotaleaguetracker.databinding.FragmentLeagueListBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LeagueListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LeagueListViewModel
    private lateinit var leagueListAdapter: LeagueListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LeagueListViewModel::class.java)
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

        val leagueListLayoutManager = LinearLayoutManager(context)
        binding.leagueList.apply {
            layoutManager = leagueListLayoutManager
            adapter = leagueListAdapter
        }
        return binding.root
    }
}

