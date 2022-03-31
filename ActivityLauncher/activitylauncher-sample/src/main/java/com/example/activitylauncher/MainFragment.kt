package com.example.activitylauncher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.activitylauncher.activities.BlueActivity
import com.example.activitylauncher.activities.GreenActivity
import com.example.activitylauncher.activities.RedActivity
import com.example.activitylauncher.databinding.FragmentMainBinding
import com.example.activitylauncher.extensions.prettyPrintId
import com.lberdiales.activitylauncher.ActivityLauncher

class MainFragment : Fragment() {

    private var listener: Listener? = null

    private val activityLauncher = ActivityLauncher(this, ActivityResultContracts.StartActivityForResult())

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRedByActivity.setOnClickListener { listener?.onRedButtonClicked(it.id) }
        binding.buttonRedByFragment.setOnClickListener {
            activityLauncher.launch(Intent(requireContext(), RedActivity::class.java)) { result ->
                printActivityLauncherResult(it.id, RedActivity::class.java.simpleName, result)
            }
        }

        binding.buttonGreenByActivity.setOnClickListener { listener?.onGreenButtonClicked(it.id) }
        binding.buttonGreenByFragment.setOnClickListener {
            activityLauncher.launch(Intent(requireContext(), GreenActivity::class.java)) { result ->
                printActivityLauncherResult(it.id, GreenActivity::class.java.simpleName, result)
            }
        }

        binding.buttonBlueByActivity.setOnClickListener { listener?.onBlueButtonClicked(it.id) }
        binding.buttonBlueByFragment.setOnClickListener {
            activityLauncher.launch(Intent(requireContext(), BlueActivity::class.java)) { result ->
                printActivityLauncherResult(it.id, BlueActivity::class.java.simpleName, result)
            }
        }
    }

    private fun printActivityLauncherResult(@IdRes originatedIn: Int, activity: String, result: ActivityResult) {
        println("MainFragment:: activityLauncher.launch($activity): origin=${resources.prettyPrintId(originatedIn)} result=$result")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    interface Listener {
        fun onRedButtonClicked(@IdRes originatedIn: Int)
        fun onGreenButtonClicked(@IdRes originatedIn: Int)
        fun onBlueButtonClicked(@IdRes originatedIn: Int)
    }
}