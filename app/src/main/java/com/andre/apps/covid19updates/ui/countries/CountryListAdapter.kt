package com.andre.apps.covid19updates.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.andre.apps.covid19updates.common.BoundListAdapter
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.databinding.CountryListItemBinding

class CountryListAdapter : BoundListAdapter<CountryItem, CountryListItemBinding>(diffCallback = object : DiffUtil.ItemCallback<CountryItem>() {
    override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean = oldItem.countrySlug == newItem.countrySlug

    override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean = oldItem.countrySlug == newItem.countrySlug && oldItem.countryName == newItem.countryName
}) {

    override fun createBinding(parent: ViewGroup, viewType: Int): CountryListItemBinding =
        CountryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: CountryListItemBinding, item: CountryItem) {
        binding.nameText.text = item.countryName
        binding.recoveredCountText.text = item.totalRecovered.toString()
        binding.confirmedCountText.text = item.totalConfirmed.toString()
        binding.deathsCountText.text = item.totalDeaths.toString()
    }
}