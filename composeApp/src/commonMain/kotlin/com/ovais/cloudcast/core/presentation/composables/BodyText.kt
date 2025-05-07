package com.ovais.cloudcast.core.presentation.composables

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ovais.cloudcast.core.presentation.primaryText


@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = primaryText,
        style = typography.bodyMedium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        modifier = modifier
    )
}