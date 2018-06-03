package app.verath.dotaleaguetracker.leaguelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import app.verath.dotaleaguetracker.MainDirections
import app.verath.dotaleaguetracker.databinding.FragmentLeagueListBinding
import app.verath.dotaleaguetracker.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LeagueListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LeagueListViewModel

    private var binding by autoCleared<FragmentLeagueListBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLeagueListBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LeagueListViewModel::class.java)

        val adapter = LeagueListAdapter {
            findNavController().navigate(MainDirections.globalShowLeagueDetails(it.leagueId))
        }
        // Connect list adapter to ViewModel
        viewModel.leagues.observe(this, Observer { leagueListResource ->
            adapter.setLeagues(leagueListResource?.data)
            adapter.notifyDataSetChanged()
        })
        // Set the list adapter on the list
        binding.leagueList.adapter = adapter
        binding.leagueList.layoutManager = LinearLayoutManager(context)

        // Listen for filter input
        binding.nameFilter.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) = viewModel.setNameFilter(s?.toString().orEmpty())
        })
    }
}
