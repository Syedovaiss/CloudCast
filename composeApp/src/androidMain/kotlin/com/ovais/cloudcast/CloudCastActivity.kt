package com.ovais.cloudcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ovais.cloudcast.core.presentation.app.CloudCast

class CloudCastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloudCast()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    CloudCast()
}
