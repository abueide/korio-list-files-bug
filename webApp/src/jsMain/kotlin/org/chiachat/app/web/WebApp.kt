package org.chiachat.app.web

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.chiachat.app.ComposeAppModules
import org.chiachat.app.compose.components.ComposeApp
import org.khronos.webgl.WebGLRenderingContext
import org.koin.core.context.startKoin
import org.w3c.dom.HTMLCanvasElement

class WebApp {
  val mainComponent = ComposeApp()

  val canvas = window.document.getElementById("ComposeTarget") as HTMLCanvasElement
  val ctx = canvas.getContext("webgl") as WebGLRenderingContext

  init {
    startKoin {
      modules(ComposeAppModules.composeModule)
    }
    resizeCanvas()
    window.onresize = { resizeCanvas() }
  }

  fun resizeCanvas() {
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight
    ctx.viewport(0, 0, window.innerWidth, window.innerHeight)
  }

  @Composable
  fun View() {
    mainComponent.View()
  }
}
