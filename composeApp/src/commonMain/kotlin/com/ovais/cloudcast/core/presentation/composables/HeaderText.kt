package com.ovais.cloudcast.core.presentation.composables

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ovais.cloudcast.core.presentation.primaryText


@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize:TextUnit = 64.sp
) {
    Text(
        text = text,
        color = primaryText,
        style = typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        modifier = modifier
    )
}