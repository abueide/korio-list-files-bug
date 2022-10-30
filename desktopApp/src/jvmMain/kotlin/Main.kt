import androidx.compose.material.Text
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.chiachat.app.ComposeAppModules
import org.chiachat.app.compose.components.ComposeApp
import org.koin.core.context.startKoin

fun main() {
  startKoin { modules(ComposeAppModules.composeModule) }
  val mainComponent = ComposeApp()
  application { Window(onCloseRequest = ::exitApplication) {
    mainComponent.View()
  } }
}
