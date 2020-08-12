package com.andre.apps.covid19updates.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.Home
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetSummary
import com.andre.apps.covid19updates.core.util.DefaultDispatcherProvider
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getSummary: GetSummary,
    private val dispatcherProvider: DispatcherProvider =
        DefaultDispatcherProvider()
) : ViewModel() {

    private val global = MutableLiveData<Result<Home>>()

    val totalConfirmed get() = global.map { it.data?.totalConfirmed }
    val totalRecovered get() = global.map { it.data?.totalRecovered }
    val totalDeaths get() = global.map { it.data?.totalDeaths }

    val errorMessage get() = global.map { it.message }

    fun loadData() {
        viewModelScope.launch {
            getSummary.execute()
                .collect {
                    global.postValue(it)
                }
        }
    }
}
