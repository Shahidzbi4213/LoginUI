package com.gulehri.loginui.screen

import android.app.Application
import androidx.compose.ui.util.fastFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gulehri.loginui.utils.CountryItem
import com.gulehri.loginui.utils.getCountries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/*
 * Created by Shahid Iqbal on 3/7/2024.
 */

class CountryViewModel(application: Application) : AndroidViewModel(application) {

    private var _searchText = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()


    private var _countries = MutableStateFlow(getCountries(application))

    val countries: StateFlow<List<CountryItem>> =
        searchText.combine(_countries) { query, countries ->
            if (query.isNotBlank())
                countries.fastFilter { it.countryName.contains(query, true) }
            else
                countries

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(1000),
            _countries.value
        )

    private var _selectedCountry =
        MutableStateFlow(_countries.value.first { it.countryName == "Pakistan" })

    val selectedCountry get() = _selectedCountry.asStateFlow()


    fun updateSelectedCountry(countryItem: CountryItem){
        _selectedCountry.value = countryItem
    }
    fun updateSearch(query: String) {
        _searchText.value = query
    }

}