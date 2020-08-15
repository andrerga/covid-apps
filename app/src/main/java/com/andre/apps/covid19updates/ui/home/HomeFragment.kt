package com.andre.apps.covid19updates.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.andre.apps.covid19updates.databinding.HomeFragmentBinding
import com.andre.apps.covid19updates.di.Injectable
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // Lazy load viewModel, not shared
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.totalConfirmed.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.confirmedCountText.text = it
        })
        viewModel.totalRecovered.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.recoveredCountText.text = it
        })
        viewModel.totalDeaths.map { it.toString() }.observe(viewLifecycleOwner, Observer {
            binding.deathsCountText.text = it
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
