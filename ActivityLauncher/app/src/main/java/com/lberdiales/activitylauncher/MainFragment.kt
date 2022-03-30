package com.lberdiales.activitylauncher

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lberdiales.activitylauncher.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var listener: Listener? = null

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        binding.buttonRed.setOnClickListener {
            listener?.onRedButtonClicked("rojo")
        }
        binding.buttonGreen.setOnClickListener {
            listener?.onGreenButtonClicked()
        }
        binding.buttonBlue.setOnClickListener {
            listener?.onBlueButtonClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    interface Listener {
        fun onRedButtonClicked(color: String)
        fun onGreenButtonClicked()
        fun onBlueButtonClicked()
    }
}