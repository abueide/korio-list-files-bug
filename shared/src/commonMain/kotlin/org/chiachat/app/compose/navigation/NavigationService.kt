package org.chiachat.app.compose.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import org.chiachat.app.compose.components.Component

class NavigationService(val initialScreen: Component, val maxScreens: Int = 10) {

    private val screenTree: MutableList<Component> = mutableListOf()
    private val currentScreenIndex: MutableState<Int> = mutableStateOf(0)
    private val currentScreen_: MutableState<Component?> = mutableStateOf(null)
    val currentScreen: State<Component?> = currentScreen_

    init {
        navigate(initialScreen)
    }

    fun navigate(screen: Component) {
        // remove any screens in the front of our screen tree since we forked off to a new branch
        if (screenTree.size > 0 && currentScreenIndex.value != screenTree.lastIndex) {
            screenTree.subList(currentScreenIndex.value + 1, screenTree.size).clear()
        }

        screenTree.add(screen)

        // Prune old screen if we are holding too many
        if (screenTree.size > maxScreens) {
            screenTree.removeFirst()
        }

        currentScreenIndex.value = screenTree.lastIndex
        updateScreen()
    }

    fun back() {
        if (currentScreenIndex.value > 0) {
            currentScreenIndex.value--
        }
        updateScreen()
    }

    fun forward() {
        if (currentScreenIndex.value < screenTree.lastIndex) {
            currentScreenIndex.value++
        }
        updateScreen()
    }

    fun clearHistory() {
        val screen = currentScreen.value
        if (screen != null) {
            screenTree.clear()
            screenTree.add(screen)
            currentScreenIndex.value = screenTree.lastIndex
            updateScreen()
        }
    }

    // Keeps the screen observable in sync with the index. Must be called wherever the screen index is changed.
    private fun updateScreen() {
        currentScreen_.value = screenTree[currentScreenIndex.value]
    }

    @Composable
    fun currentView() {
        currentScreen.value?.view()
    }
}
