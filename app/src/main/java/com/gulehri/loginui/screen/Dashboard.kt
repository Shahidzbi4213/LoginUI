package com.gulehri.loginui.screen;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.Black
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.MainGradient
import com.gulehri.loginui.ui.theme.OrangeMain
import com.ramcosta.composedestinations.annotation.Destination

/*
 * Created by Shahid Iqbal on 3/18/2024.
 */

@Destination
@Composable
fun DashboardScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.welcome),
            modifier = Modifier.fillMaxWidth(),
            style = Header,
            color = OrangeMain,
            textAlign = TextAlign.Center
        )

    }
}