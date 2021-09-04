package live.adabe.findmyblood

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import live.adabe.findmyblood.databinding.ActivityMainBinding
import androidx.drawerlayout.widget.DrawerLayout
import live.adabe.findmyblood.utils.Preferences


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.navToolBar)

        NavigationUI.setupWithNavController(binding.navDrawer, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        val header = binding.navDrawer.getHeaderView(0)
        val headerHospitalName = header.findViewById<TextView>(R.id.headerHospitalName)
        headerHospitalName.text = Preferences(this).getHospitalName()


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.landingFragment -> {
                    supportActionBar?.hide()
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }
}