package app.verath.dotaleaguetracker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_league_list.view.*


class LeagueListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LeagueListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_league_list, container, false)
        view.btnToDetails.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_league_details))
        return view
    }
}
