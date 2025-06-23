package com.example.paddel.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Text("Onboarding placeholder")
    }
}
