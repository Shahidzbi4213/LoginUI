package com.gulehri.loginui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.ButtonTextStyle
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.GrayUnSelected
import com.gulehri.loginui.ui.theme.Header
import com.ramcosta.composedestinations.annotation.Destination

/*
 * Created by Shahid Iqbal on 3/10/2024.
 */

@Destination
@Composable
fun OtpScreen(
    phoneNumber: String,
    phoneCode: String,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel()
) {

    val otp by authViewModel.otp.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .navigationBarsPadding()
            .statusBarsPadding()
            .scrollable(rememberScrollState(), Orientation.Vertical),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null
                )
            }

            Text(
                text = stringResource(id = R.string.otp_verfication),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = Header.copy(fontSize = 20.sp),
                color = Color.Black
            )
        }

        Image(
            painter = painterResource(id = R.drawable.otp_verfication),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth()
                .height(250.dp)
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally)
        )

        OTPBottomSection(phoneNumber, phoneCode, otp) {
            authViewModel.updateOtp(it)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OTPBottomSection(
    phoneNumber: String,
    phoneCode: String,
    otp: String,
    onUpdate: (Char) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),

        ) {
        Text(
            text = stringResource(id = R.string.enter_otp),
            style = Header.copy(fontSize = 22.sp),
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 40.dp)
        )

        Text(
            text = stringResource(id = R.string.otp_4_digits),
            style = Description.copy(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            ),
            color = DescriptionColor.copy(alpha = 0.8f),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 5.dp),
            textAlign = TextAlign.Start
        )

        Text(
            text = "(+$phoneCode) $phoneNumber",
            style = Description.copy(
                fontSize = 14.sp, fontWeight = FontWeight.SemiBold
            ),
            color = DescriptionColor.copy(alpha = 0.8f),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 5.dp)


        )


        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            repeat(4) {
                OTPBox(otp.getOrNull(it), onUpdate)
            }
        }
    }
}


@Composable
fun OTPBox(otp: Char?, onUpdate: (Char) -> Unit, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .size(55.dp)
            .shadow(
                1.dp, shape = RoundedCornerShape(10.dp), ambientColor = Color(0x33000000)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(color = GrayUnSelected)
    ) {
        TextField(
            value = "$otp",
            onValueChange = { onUpdate(it[0]) },
            textStyle = ButtonTextStyle,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}