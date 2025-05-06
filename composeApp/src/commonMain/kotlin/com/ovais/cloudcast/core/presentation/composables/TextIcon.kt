package com.ovais.cloudcast.core.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun TextIcon(
    resource: DrawableResource,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(resource),
            contentDescription = title,
            modifier = Modifier
                .padding(
                    all = 16.dp
                )
                .width(32.dp)
                .height(32.dp)
        )
        BodyText(
            text = title,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )
    }
}