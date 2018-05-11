package app.verath.dotaleaguetracker


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_league_details.view.*


class LeagueDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LeagueDetailsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_league_details, container, false)
        return view
    }


}