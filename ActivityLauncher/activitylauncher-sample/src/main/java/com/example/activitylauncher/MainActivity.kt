package com.example.activitylauncher

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.activitylauncher.activities.BlueActivity
import com.example.activitylauncher.activities.GreenActivity
import com.example.activitylauncher.activities.RedActivity
import com.example.activitylauncher.databinding.ActivityMainBinding
import com.lberdiales.activitylauncher.ActivityLauncher

class MainActivity : AppCompatActivity(), MainFragment.Listener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val activityLauncher = ActivityLauncher(this, ActivityResultContracts.StartActivityForResult())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }

    override fun onRedButtonClicked(color: String) {
        activityLauncher.launch(Intent(this, RedActivity::class.java)) { result ->
            println("activityLauncher.launch(RedActivity::class.java).result:$result, desde el botón:$color")
        }
    }

    override fun onGreenButtonClicked() {
        activityLauncher.launch(Intent(this, GreenActivity::class.java)) { result ->
            println("activityLauncher.launch(GreenActivity::class.java).result:$result")
        }
    }

    override fun onBlueButtonClicked() {
        activityLauncher.launch(Intent(this, BlueActivity::class.java)) { result ->
            println("activityLauncher.launch(BlueActivity::class.java).result:$result")
        }
        finish()
    }
}
