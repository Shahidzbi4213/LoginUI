package com.gulehri.loginui.ui.screens;

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.screens.destinations.OnBoardingScreenDestination
import com.gulehri.loginui.ui.screens.destinations.SplashScreenDestination
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.MainGradient
import com.gulehri.loginui.ui.theme.loginFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/*
 * Created by Shahid Iqbal on 2/21/2024.
 */




@OptIn(ExperimentalFoundationApi::class)
@RootNavGraph(true)
@Destination
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {

    LaunchedEffect(key1 = Unit, block = {
        delay(3.seconds)

        navigator.navigate(
            OnBoardingScreenDestination,
            builder = { popUpTo(SplashScreenDestination) { inclusive = true } })
    })

    ConstraintLayout(
        Modifier
            .background(brush = MainGradient)
            .fillMaxSize()
    ) {

        val (logo, title,
            description, bottomIcon,
            bottomText) = createRefs()


        Image(painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {

                    linkTo(
                        top = parent.top,
                        bottom = parent.bottom,
                        start = parent.start,
                        end = parent.end,
                        verticalBias = 0.4f
                    )
                    width = Dimension.preferredWrapContent
                    height = Dimension.preferredWrapContent

                }
                .padding(vertical = 5.dp))

        Text(text = stringResource(id = R.string.aliments),
            color = Color.White,
            fontSize = 24.sp,
            style = Header,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(logo.bottom)
            })


        Text(text = stringResource(id = R.string.food_delivery_service),
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = loginFontFamily,
            fontWeight = FontWeight.Light,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


        Text(text = stringResource(id = R.string.loopr).uppercase(),
            fontFamily = loginFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier
                .constrainAs(bottomText) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                .padding(bottom = 20.dp))

        Image(painter = painterResource(id = R.drawable.loopr_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(bottomIcon) {
                    bottom.linkTo(bottomText.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(vertical = 5.dp))


    }

}