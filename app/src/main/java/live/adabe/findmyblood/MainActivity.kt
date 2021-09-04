package live.adabe.findmyblood

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import live.adabe.findmyblood.databinding.ActivityMainBinding
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

        NavigationUI.setupWithNavController(binding.navDrawer, navController)

        val header = binding.navDrawer.getHeaderView(0)
        val headerHospitalName = header.findViewById<TextView>(R.id.headerHospitalName)
        headerHospitalName.text = Preferences(this).getHospitalName()

        val profileHeader = binding.navDrawer.getHeaderView(0)
        val profileHeaderImage = profileHeader.findViewById<ImageView>(R.id.headerProfileImage)
        Glide.with(this).load(Preferences(this).getImage())
            .into(profileHeaderImage)
            .onLoadFailed(applicationContext.getDrawable(R.drawable.ic_profile))

        binding.navDrawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    binding.drawerLayout.close()
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    binding.drawerLayout.close()
                }

                R.id.requestScreenFragment -> {
                    navController.navigate(R.id.requestScreenFragment)
                    binding.drawerLayout.close()
                }

                R.id.dashboardScreenFragment -> {
                    navController.navigate(R.id.dashboardScreenFragment)
                    binding.drawerLayout.close()
                }

                R.id.logOut -> {
                    Preferences(this).clear()
                    finish()
                }
            }
            true
        }
    }
}