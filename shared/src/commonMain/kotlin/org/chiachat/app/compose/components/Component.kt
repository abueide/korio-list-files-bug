package org.chiachat.app.compose.components

import androidx.compose.runtime.Composable
import org.chiachat.app.compose.navigation.NavigationService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Component : KoinComponent {
    val navigationService: NavigationService by inject()
    @Composable
    abstract fun view()
}
