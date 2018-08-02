package me.tabacowang.giveyouapunch

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.tabacowang.giveyouapunch.util.setupActionBar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    lateinit var drawerLayout: DrawerLayout

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupActionBar(R.id.toolbar) {}

        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }

        val navController = findNavController(R.id.navHostFragment)

        setupNavActionBar(navController)

        setupDrawerContent(findViewById(R.id.nav_view))
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout, Navigation.findNavController(this, R.id.navHostFragment))
    }

    private fun setupNavActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_navigation_menu_item -> {
                    // Do nothing, we're already on that screen
                }
                R.id.statistics_navigation_menu_item -> {
//                    val intent = Intent(this@TasksActivity, StatisticsActivity::class.java).apply {
//                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    }
//                    startActivity(intent)
                }
            }
            // Close the navigation drawer when an item is selected.
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
    }
}
