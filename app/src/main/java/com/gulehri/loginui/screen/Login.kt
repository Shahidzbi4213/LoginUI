package com.gulehri.loginui.screen

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.Black
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.GrayUnSelected
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.TabUnSeleced
import com.gulehri.loginui.utils.noRippleClickable
import com.ramcosta.composedestinations.annotation.Destination

/*
 * Created by Shahid Iqbal on 3/3/2024.
 */

@Destination
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    val tabs = listOf(R.string.phone_number, R.string.email)

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                horizontal = 30.dp,
                vertical = 20.dp
            )
            .scrollable(
                rememberScrollState(),
                Orientation.Vertical
            )
    ) {

        Text(
            text = stringResource(id = R.string.login_account),
            style = Header,
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(7.dp))


        Text(
            text = stringResource(id = R.string.welcome_back),
            style = Description,
            fontSize = 14.sp,
            color = DescriptionColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))


        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = GrayUnSelected),
            verticalAlignment = Alignment.CenterVertically
        ) {

            tabs.forEachIndexed { index, tab ->

                MyTab(
                    text = tab,
                    selectedTabIndex == index,
                    index
                ) {

                    selectedTabIndex = it
                }
            }

        }
    }
}


@Composable
fun RowScope.MyTab(
    @StringRes text: Int,
    isSelected: Boolean,
    index: Int, onClick: (Int) -> Unit
) {

    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Color.Black
        } else {
            TabUnSeleced
        },
        animationSpec = tween(
            100, 100, easing = LinearEasing
        ),
        label = "Text Color",
    )

    Box(
        modifier = Modifier
            .weight(1f)
            .noRippleClickable { onClick.invoke(index) },
        contentAlignment = Alignment.Center
    ) {

        if (isSelected) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 7.dp, horizontal = 7.dp),
                elevation = CardDefaults.elevatedCardElevation(1.5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                content = {})
        }

        Text(
            text = stringResource(id = text),
            style = Description,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = tabTextColor,
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent)


        )
    }
}