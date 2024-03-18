package com.gulehri.loginui.ui.screens;

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
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
fun DashboardScreen(user: SignInCredential? = null) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = user?.profilePictureUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .shadow(elevation = 5.dp, shape = CircleShape)
                .clip(CircleShape)
                .border(4.dp, brush = MainGradient, shape = CircleShape)
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.welcome).plus(" ").plus(user?.displayName),
            modifier = Modifier.fillMaxWidth(),
            style = Header,
            color = OrangeMain,
            textAlign = TextAlign.Center
        )

    }
}