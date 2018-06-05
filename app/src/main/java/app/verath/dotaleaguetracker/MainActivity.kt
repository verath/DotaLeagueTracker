package app.verath.dotaleaguetracker

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import app.verath.dotaleaguetracker.util.autoCleared

class MainActivity : AppCompatActivity(), NavController.OnNavigatedListener {

    private var navController by autoCleared<NavController>()
    private var drawerLayout by autoCleared<DrawerLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        drawerLayout = findViewById(R.id.drawer_layout)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnNavigatedListener(this)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnNavigatedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigateUp() = navController.navigateUp()

    override fun onNavigated(controller: NavController, destination: NavDestination) {
        // Hide the sidebar after navigation, avoids having to lookup
        // the drawer layout from the nested fragments
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }
}
