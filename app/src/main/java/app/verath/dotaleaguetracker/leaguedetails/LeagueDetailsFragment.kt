package app.verath.dotaleaguetracker.leaguedetails


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.verath.dotaleaguetracker.databinding.FragmentLeagueDetailsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class LeagueDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LeagueDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Locate ViewModel, using our viewModelFactory for DI
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LeagueDetailsViewModel::class.java)

        // Update viewModel from navigation arguments
        LeagueDetailsFragmentArgs.fromBundle(arguments).apply {
            viewModel.setLeagueId(leagueId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentLeagueDetailsBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        return binding.root
    }


}
