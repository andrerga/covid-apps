package com.andre.apps.covid19updates.ui.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.databinding.CountryListFragmentBinding
import com.andre.apps.covid19updates.di.Injectable
import javax.inject.Inject

class CountryListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // Lazy load viewModel, shared by main activity
    private val viewModel by activityViewModels<CountryListViewModel> { viewModelFactory }

    private var _binding: CountryListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CountryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CountryListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList()
    }

    private fun initializeList() {
        adapter = CountryListAdapter(viewModel)
        binding.countryRecycler.addItemDecoration(CountryListItemDivider(context))
        binding.countryRecycler.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> adapter.submitList(it.data!!)
                else -> adapter.submitList(emptyList())
            }
            adapter.notifyDataSetChanged()
        })

        viewModel.getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
