package com.gulehri.loginui.screen;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.Black
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.GrayUnSelected
import com.gulehri.loginui.utils.CountryItem
import com.gulehri.loginui.utils.noRippleClickable

/*
 * Created by Shahid Iqbal on 3/6/2024.
 */



@Composable
fun SingleCountryItem(
    item: CountryItem, modifier: Modifier = Modifier, onClick: () -> Unit
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .noRippleClickable { onClick() }) {

        Text(
            text = item.countryFlag,
            fontSize = 18.sp,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp)
        )

        Text(
            text = item.countryName.plus(" ").plus("(+").plus(item.phoneCode).plus(")"),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            style = Description,
            fontSize = 14.sp,
            color = Black,
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerSheet(
    searchViewModel: SearchViewModel = viewModel(),
    onClick: (CountryItem) -> Unit,
    dismiss: () -> Unit
) {


    val countries by searchViewModel.countries.collectAsStateWithLifecycle()
    val query by searchViewModel.searchText.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = dismiss,
        containerColor = Color.White,

        ) {

        SearchBar(
            query = query,
            onQueryChange = searchViewModel::updateSearch,
            onSearch = searchViewModel::updateSearch,
            active = true,
            onActiveChange = {},
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_country),
                    style = Description,
                    color = DescriptionColor
                )
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color.Transparent,
                dividerColor = DescriptionColor,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = DescriptionColor,
                    cursorColor = DescriptionColor,
                    focusedTrailingIconColor = DescriptionColor
                ),
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {

                items(countries, key = { item: CountryItem -> item.countryName }) {
                    SingleCountryItem(item = it) {
                        onClick(it)
                    }
                }
            }
        }

    }

}