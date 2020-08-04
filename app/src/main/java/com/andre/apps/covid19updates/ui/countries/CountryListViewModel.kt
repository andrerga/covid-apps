package com.andre.apps.covid19updates.ui.countries

import androidx.lifecycle.*
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetCountryInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryListViewModel @Inject constructor(
    private val getCountryInfo: GetCountryInfo
) : ViewModel() {

    private val _items = MediatorLiveData<Result<List<CountryItem>>>()
    val items get() = _items as LiveData<Result<List<CountryItem>>>

    fun getData() {
        viewModelScope.launch {
            val data = getCountryInfo.execute().asLiveData()
            _items.addSource(data) {
                _items.postValue(it)
            }
        }
    }
}