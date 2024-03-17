package com.gulehri.loginui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gulehri.loginui.R
import com.gulehri.loginui.ui.theme.Black
import com.gulehri.loginui.ui.theme.ButtonTextStyle
import com.gulehri.loginui.ui.theme.Description
import com.gulehri.loginui.ui.theme.DescriptionColor
import com.gulehri.loginui.ui.theme.ErrorColor
import com.gulehri.loginui.ui.theme.GrayUnSelected
import com.gulehri.loginui.ui.theme.Header
import com.gulehri.loginui.ui.theme.MainGradient
import com.gulehri.loginui.ui.theme.OTPErrorColor
import com.gulehri.loginui.ui.theme.OrangeMain
import com.gulehri.loginui.utils.NoRippleInteractionSource
import com.gulehri.loginui.utils.debug
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

/*
 * Created by Shahid Iqbal on 3/10/2024.
 */

const val OTP_LENGTH = 4

@Destination
@Composable
fun OtpScreen(
    phoneNumber: String,
    phoneCode: String,
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    authViewModel: AuthViewModel = viewModel(),
) {


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

            IconButton(onClick = {
                navigator.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
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

        OTPBottomSection(phoneNumber, phoneCode, authViewModel)
    }
}

@Composable
fun OTPBottomSection(
    phoneNumber: String,
    phoneCode: String,
    authViewModel: AuthViewModel,
) {

    val otp by authViewModel.otp.collectAsStateWithLifecycle()
    val isOtpValid by authViewModel.isOtpValid.collectAsStateWithLifecycle()
    val keyboardState = LocalSoftwareKeyboardController.current

    var verifyOtp by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = verifyOtp) {

        if (verifyOtp) {
            delay(3000)
            verifyOtp = false
        }
    }

    LaunchedEffect(key1 = otp) {
        if (otp.length == OTP_LENGTH)
            keyboardState?.hide()
    }


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
            text = stringResource(id = R.string.otp_4_digits, OTP_LENGTH),
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

        OTPSection(
            otp = otp,
            onUpdate = authViewModel::updateOtp,
            isValid = isOtpValid
        )

        if (!isOtpValid) {
            Text(
                text = stringResource(id = R.string.invalid_otp),
                color = ErrorColor,
                style = Description.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                verifyOtp = true
            },
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
                    if (!isOtpValid && !verifyOtp) MainGradient
                    else Brush.linearGradient(
                        listOf(
                            OrangeMain, OrangeMain
                        )
                    )
                ),
            enabled = isOtpValid && otp.length == 4 && !verifyOtp

        ) {

            if (verifyOtp)
                CircularProgressIndicator(
                    color = Color.White,
                    strokeCap = StrokeCap.Butt,
                    strokeWidth = 3.dp,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically)

                )

            Text(
                text = stringResource(id = R.string.verif),
                style = ButtonTextStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.resend_otp),
            style = Header.copy(fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp),
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
            ,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun OTPSection(
    otpLength: Int = OTP_LENGTH, otp: String, onUpdate: (String) -> Unit,
    isValid: Boolean,
    modifier: Modifier = Modifier,
) {


    BasicTextField(
        value = otp,
        onValueChange = {
            if (it.length <= otpLength) onUpdate(it)
        },
        modifier = modifier.padding(top = 25.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
        maxLines = 1,
        decorationBox = {

            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                repeat(otpLength) { index ->

                    val otpCharacter = otp.getOrNull(index)?.toString() ?: ""


                    Box(
                        modifier = modifier
                            .size(65.dp)
                            .shadow(
                                2.dp,
                                shape = RoundedCornerShape(10.dp),
                                ambientColor = Color(0x33000000)
                            )
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = if (isValid) GrayUnSelected else OTPErrorColor),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        Text(
                            text = otpCharacter,
                            modifier = modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .align(Alignment.Center),

                            style = ButtonTextStyle.copy(
                                fontWeight = FontWeight.Bold, fontSize = 24.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        },
        textStyle = ButtonTextStyle.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
        cursorBrush = SolidColor(Black)
    )


}