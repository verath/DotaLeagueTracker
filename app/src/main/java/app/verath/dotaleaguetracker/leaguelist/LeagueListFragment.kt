package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        // Create the list adapter
        leagueListAdapter = LeagueListAdapter({
            findNavController().navigate(
                    LeagueListFragmentDirections.showLeagueDetails(it.leagueId))
        })

        // Locate ViewModel, using our viewModelFactory for DI
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LeagueListViewModel::class.java)

        // Connect list adapter to ViewModel
        viewModel.leagues.observe(this, Observer {
            it?.run {
                leagueListAdapter.setLeagues(it)
                leagueListAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLeagueListBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)

        // Connect the RecylerView to the list adapter
        binding.leagueList.apply {
            adapter = leagueListAdapter
            layoutManager = LinearLayoutManager(LeagueListFragment@ context)
        }

        return binding.root
    }
}
