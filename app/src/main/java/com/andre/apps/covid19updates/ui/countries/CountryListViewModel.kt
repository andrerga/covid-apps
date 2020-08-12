package com.andre.apps.covid19updates.ui.countries

import androidx.lifecycle.*
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetCountryInfo
import com.andre.apps.covid19updates.nav.NavManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val navManager: NavManager,
    private val getCountryInfo: GetCountryInfo
) : ViewModel() {

    private val _items = MutableLiveData<Result<List<CountryItem>>>()
    val items get() = _items as LiveData<Result<List<CountryItem>>>

    private val _selectedItem = MutableLiveData<CountryItem>()
    val selectedCountryName get() = _selectedItem.map { it.countryName }

    fun getData() {
        viewModelScope.launch {
            getCountryInfo.execute()
                .collect {
                    _items.postValue(it)
                }
        }
    }

    fun selectItem(item: CountryItem) {
        _selectedItem.postValue(item)
        // Go To Country detail
        navManager.navigate(
            CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment()
        )
    }
}
