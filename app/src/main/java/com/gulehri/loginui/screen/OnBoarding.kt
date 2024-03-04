package com.gulehri.loginui.screen;

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gulehri.loginui.R
import com.gulehri.loginui.screen.destinations.LoginScreenDestination
import com.gulehri.loginui.ui.theme.Black
import com.gulehri.loginui.ui.theme.ButtonTextStyle
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.GrayDeep
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.MainGradient
import com.gulehri.loginui.ui.theme.OrangeMain
import com.gulehri.loginui.utils.NoRippleInteractionSource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/*
 * Created by Shahid Iqbal on 2/28/2024.
 */

data class OnboardItem(
    @DrawableRes val image: Int, @StringRes val title: Int, @StringRes val description: Int
)

val pagesList = listOf<OnboardItem>(
    OnboardItem(R.drawable.page1, R.string.page1_title, R.string.page1_description),
    OnboardItem(R.drawable.page2, R.string.page2_title, R.string.page2_description),
    OnboardItem(R.drawable.page3, R.string.page3_title, R.string.page3_description),
)


@Destination
@ExperimentalFoundationApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {


    val pagerState = rememberPagerState(pageCount = { pagesList.size })


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState, modifier = Modifier.weight(2f)
        ) {

            SinglePage(onboardItem = pagesList[it], modifier = Modifier.fillMaxSize())
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) {
                PageIndicator(it == pagerState.currentPage)
            }
        }

        BottomButtons(modifier = Modifier.weight(0.75f),
            onLoginClick = {
                navigator.navigate(LoginScreenDestination)
            }, onCreate = {})


    }

}

@Composable
fun PageIndicator(selected: Boolean) {

    Canvas(modifier = Modifier.size(20.dp), onDraw = {

        drawCircle(color = if (selected) OrangeMain else GrayDeep, 15f)

    })
}

@Composable
fun BottomButtons(
    modifier: Modifier = Modifier, onLoginClick: () -> Unit, onCreate: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MainGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = onLoginClick,
            interactionSource = NoRippleInteractionSource(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(0.85f)

        ) {


            Text(
                text = stringResource(id = R.string.login), style = ButtonTextStyle
            )

        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onCreate,
            shape = RoundedCornerShape(10.dp),
            interactionSource = NoRippleInteractionSource(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Black, contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(0.85f)

        ) {

            Text(
                text = stringResource(id = R.string.create_an_account),
                style = ButtonTextStyle,
                textAlign = TextAlign.Center,
            )

        }

    }
}


@Composable
fun SinglePage(onboardItem: OnboardItem, modifier: Modifier = Modifier) {

    Column(
        modifier.background(Color.White),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = onboardItem.image),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = onboardItem.title),
            style = Header,
            color = OrangeMain,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = onboardItem.description),
            style = Description,
            color = DescriptionColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

    }
}