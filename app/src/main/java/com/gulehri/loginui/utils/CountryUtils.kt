package com.gulehri.loginui.utils

import android.content.Context
import com.gulehri.loginui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.Locale

/*
 * Created by Shahid Iqbal on 3/6/2024.
 */



data class CountryItem(
    val countryName: String,
    val countryCode: String,
    val countryFlag: String,
    val phoneCode: String
)


fun getCountries(context: Context): List<CountryItem> {

    return context.resources.getStringArray(R.array.CountryCodes)
        .map {
            CountryItem(
                countryName = Locale("", it.substringAfter(",")).displayCountry,
                countryCode = "+".plus(it.substringAfter(",")),
                phoneCode = it.substringBefore(","),
                countryFlag = CountryFlags.getCountryFlag(it.substringAfter(",").lowercase())
            )

        }
}