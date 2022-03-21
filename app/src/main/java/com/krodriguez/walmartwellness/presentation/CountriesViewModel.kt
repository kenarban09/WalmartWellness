package com.krodriguez.walmartwellness.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krodriguez.walmartwellness.data.remote.state.APIState
import com.krodriguez.walmartwellness.domain.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val useCase: GetCountriesUseCase
) : ViewModel() {

    private val _countriesLiveData = MutableLiveData<APIState>()
    val countriesLiveData: LiveData<APIState>
        get() = _countriesLiveData

    fun getCountries() {
        viewModelScope.launch {
            useCase.execute()
                .catch {
                    _countriesLiveData.value = APIState.Error("No Internet Connection")
                }
                .collect { dataState ->
                    _countriesLiveData.value = dataState
                }
        }
    }
}
