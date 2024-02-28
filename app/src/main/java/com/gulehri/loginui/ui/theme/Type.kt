package com.gulehri.loginui.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gulehri.loginui.R

val loginFontFamily = FontFamily(
    Font(R.font.poppins, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = loginFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
)

val Header = TextStyle(
    fontFamily = loginFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val Description = TextStyle(
    fontFamily = loginFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
)

val ButtonTextStyle = TextStyle(
    fontFamily = loginFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp
)
