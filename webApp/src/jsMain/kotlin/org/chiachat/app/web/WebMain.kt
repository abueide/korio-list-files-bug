package org.chiachat.app.web

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
  console.log("Test")
  onWasmReady {
    val app = WebApp()
    Window("ChiaChat") {
      app.View()
    }
  }
}
