package com.andre.apps.covid19updates.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andre.apps.covid19updates.databinding.CountryDetailFragmentBinding
import com.andre.apps.covid19updates.di.Injectable
import com.andre.apps.covid19updates.ui.MainActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CountryDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // Lazy load viewModel, shared by main activity
    private val viewModel by viewModels<CountryListViewModel>({ activity as MainActivity }) { viewModelFactory }

    private var _binding: CountryDetailFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = CountryDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedCountryName.observe(viewLifecycleOwner, Observer {
            binding.countryNameText.text = it
        })
    }
}
