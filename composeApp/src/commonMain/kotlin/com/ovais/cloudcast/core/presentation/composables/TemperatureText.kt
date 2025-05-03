package com.ovais.cloudcast.core.presentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ovais.cloudcast.core.presentation.primaryText


@Composable
fun TemperatureText(
    digitSize: TextUnit = 64.sp,
    digitText: String,
    degreeSize: TextUnit = 72.sp,
    degreeText: String = "°",
    weatherType: String,
    weatherSize: TextUnit = 32.sp,
    textColor:Color = primaryText
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = digitSize)) {
                append(digitText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = degreeSize,
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                append(degreeText)
            }
            withStyle(
                style = SpanStyle(
                    fontSize = weatherSize,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                append(weatherType)
            }
        },
        color = textColor
    )
}