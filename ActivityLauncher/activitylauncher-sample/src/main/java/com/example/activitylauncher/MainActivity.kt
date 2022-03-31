package com.example.activitylauncher

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.activitylauncher.activities.BlueActivity
import com.example.activitylauncher.activities.GreenActivity
import com.example.activitylauncher.activities.RedActivity
import com.example.activitylauncher.databinding.ActivityMainBinding
import com.example.activitylauncher.extensions.prettyPrintId
import com.lberdiales.activitylauncher.ActivityLauncher

class MainActivity : AppCompatActivity(), MainFragment.Listener {

    private val activityLauncher = ActivityLauncher(this, ActivityResultContracts.StartActivityForResult())

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

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

    override fun onRedButtonClicked(@IdRes originatedIn: Int) {
        activityLauncher.launch(Intent(this, RedActivity::class.java)) { result ->
            printActivityLauncherResult(originatedIn, RedActivity::class.java.simpleName, result)
        }
    }

    override fun onGreenButtonClicked(@IdRes originatedIn: Int) {
        activityLauncher.launch(Intent(this, GreenActivity::class.java)) { result ->
            printActivityLauncherResult(originatedIn, GreenActivity::class.java.simpleName, result)
        }
    }

    override fun onBlueButtonClicked(@IdRes originatedIn: Int) {
        activityLauncher.launch(Intent(this, BlueActivity::class.java)) { result ->
            printActivityLauncherResult(originatedIn, BlueActivity::class.java.simpleName, result)
        }
        finish()
    }

    private fun printActivityLauncherResult(@IdRes originatedIn: Int, activity: String, result: ActivityResult) {
        println("MainActivity:: activityLauncher.launch($activity): origin=${resources.prettyPrintId(originatedIn)} result=$result")
    }
}

