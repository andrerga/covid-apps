package com.andre.apps.covid19updates.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.andre.apps.covid19updates.databinding.NewsFragmentBinding
import com.andre.apps.covid19updates.di.Injectable
import javax.inject.Inject

class NewsFragment : Fragment(), Injectable {

    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // Lazy load viewModel, not shared
    private val viewModel by viewModels<NewsViewModel> { viewModelFactory }

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList()
    }

    private fun initializeList() {

        adapter = NewsAdapter(viewModel)
        binding.newsRecycler.adapter = adapter

        viewModel.news.observe(viewLifecycleOwner, Observer { result ->
            result.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.newsRecycler.adapter = null
        binding.newsRecycler.removeAllViews()

        _binding = null
    }
}
