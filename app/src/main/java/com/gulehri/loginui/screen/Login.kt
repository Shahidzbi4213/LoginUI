package com.gulehri.loginui.screen

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.ButtonTextStyle
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.DividerColor
import com.gulehri.loginui.ui.theme.GrayUnSelected
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.MainGradient
import com.gulehri.loginui.ui.theme.OrangeMain
import com.gulehri.loginui.ui.theme.TabUnSelected
import com.gulehri.loginui.utils.NoRippleInteractionSource
import com.gulehri.loginui.utils.customFieldsColors
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
            .background(color = Color.White)
            .padding(
                horizontal = 30.dp, vertical = 20.dp
            )
            .scrollable(
                rememberScrollState(), Orientation.Vertical
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
                    text = tab, selectedTabIndex == index, index
                ) {

                    selectedTabIndex = it
                }
            }

        }

        Spacer(modifier = Modifier.height(60.dp))

        if (selectedTabIndex == 1) {
            LoginWithEmail(modifier = Modifier.fillMaxWidth())
        }

        Spacer(modifier = Modifier.height(40.dp))

        OrDivider()

        Spacer(modifier = Modifier.height(50.dp))

        LoginWithGoogleBtn()

        Spacer(modifier = Modifier.height(30.dp))


        CreateAccount(modifier = Modifier.fillMaxWidth())


    }
}


@Composable
fun CreateAccount(modifier: Modifier = Modifier) {

    Row(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.not_registered),
            color = Color(0xFF979797),
            style = Description,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(id = R.string.create_an_account),
            color = OrangeMain,
            style = Description,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun LoginWithGoogleBtn() {

    Card(
        onClick = { },
        interactionSource = NoRippleInteractionSource(),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),


        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = null,
                modifier = Modifier.padding(start = 10.dp)
            )

            Text(
                text = stringResource(id = R.string.login_with_google),
                style = ButtonTextStyle,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
private fun OrDivider(modifier: Modifier = Modifier) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            thickness = 2.dp,
            color = DividerColor
        )

        Text(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(horizontal = 10.dp),
            style = ButtonTextStyle,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        HorizontalDivider(
            modifier = Modifier
                .weight(1f),
            thickness = 2.dp,
            color = DividerColor
        )
    }
}

@Composable
fun LoginWithEmail(modifier: Modifier = Modifier) {

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var enableState by remember {
        mutableStateOf(email.isNotBlank() && password.isNotEmpty())
    }

    LaunchedEffect(key1 = email, key2 = password) {

        enableState = email.isNotEmpty() && password.isNotEmpty()
    }

    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.email_id),
                    style = Description,
                    color = DescriptionColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            textStyle = Description.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DescriptionColor,
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email_leading_icon),
                    contentDescription = null
                )
            },
            shape = RectangleShape,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Email
            ),
            colors = customFieldsColors(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            textStyle = Description.copy(fontSize = 16.sp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password),
                    style = Description,
                    color = DescriptionColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password_leading_icon),
                    contentDescription = null
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = customFieldsColors(),
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(40.dp))


        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            interactionSource = NoRippleInteractionSource(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (!enableState) MainGradient else Brush.linearGradient(
                        listOf(
                            OrangeMain,
                            OrangeMain
                        )
                    )
                ),
            enabled = enableState

        ) {

            Text(
                text = stringResource(id = R.string.login),
                style = ButtonTextStyle,
                textAlign = TextAlign.Center,
            )

        }
    }

}


@Composable
fun RowScope.MyTab(
    @StringRes text: Int, isSelected: Boolean, index: Int, onClick: (Int) -> Unit
) {

    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Color.Black
        } else {
            TabUnSelected
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
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 7.dp, horizontal = 7.dp),
                elevation = CardDefaults.elevatedCardElevation(2.dp),
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