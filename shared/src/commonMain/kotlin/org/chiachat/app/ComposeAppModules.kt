package org.chiachat.app

import org.chiachat.app.compose.components.ResourceLoadDebugComponent
import org.chiachat.app.compose.navigation.NavigationService
import org.koin.dsl.module

object ComposeAppModules {

  val composeModule = module {
    single { NavigationService(ResourceLoadDebugComponent()) }
  }

}
