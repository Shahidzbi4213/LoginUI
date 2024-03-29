package com.gulehri.loginui.ui.viewmodel

import android.app.Application
import androidx.compose.ui.util.fastFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gulehri.loginui.utils.CountryItem
import com.gulehri.loginui.utils.getCountries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 3/7/2024.
 */

class AuthViewModel(private val application: Application) : AndroidViewModel(application) {

    private var _searchText = MutableStateFlow("")
    val searchText get() = _searchText.asStateFlow()
    private var _countries = MutableStateFlow(getCountries(application))




    private var _otp = MutableStateFlow("")
    val otp get() = _otp.asStateFlow()

    val isOtpValid = otp.map {
        when {
            it.length < 4 -> true
            it.length == 4 && it == "1998" -> true
            it.length == 4 && otp.value != "1998" -> false
            else -> false
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, true)


    val countries =
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


    fun updateSelectedCountry(countryItem: CountryItem) {
        _selectedCountry.value = countryItem
    }

    fun updateSearch(query: String) {
        _searchText.value = query
    }

    fun updateOtp(value: String) {
        viewModelScope.launch {
            _otp.value = value
        }
    }

}