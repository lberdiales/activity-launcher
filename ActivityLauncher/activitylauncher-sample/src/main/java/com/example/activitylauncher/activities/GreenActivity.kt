package com.example.activitylauncher.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.activitylauncher.R

class GreenActivity : AppCompatActivity(R.layout.activity_green) {

    override fun onBackPressed() {
        setResult(RESULT_OK, Intent().apply {
            putExtra(EXTRA_STRING, "algún extra")
        })
        super.onBackPressed()
    }

    companion object {
        const val EXTRA_STRING = "STRING"
    }
}
