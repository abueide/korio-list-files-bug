package org.chiachat.app.compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soywiz.korio.file.VfsFile
import com.soywiz.korio.file.baseName
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResourceLoadDebugComponent: Component() {
    val resources = listOf(
        "fonts/Chewy-Regular.ttf",
        "fonts/OFL.txt",
        "fonts/Oxygen-Bold.ttf",
        "fonts/Oxygen-Light.ttf",
        "fonts/Oxygen-Regular.ttf",
    ).map { resourcesVfs[it] }
    val fontList: MutableStateFlow<List<VfsFile>> = MutableStateFlow(emptyList())
    val filesExistList: MutableStateFlow<Map<VfsFile, Boolean>> = MutableStateFlow(emptyMap())

    init {
        CoroutineScope(Dispatchers.Default).launch {
            resources.forEach {
                filesExistList.value += it to it.exists()
            }
            fontList.value = resourcesVfs["fonts/"].listSimple()
        }
    }

    @Composable
    override fun view() {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(1f))
            listFontDir()
            filesExist()
            Spacer(Modifier.weight(1f))
        }
    }

    @Composable fun listFontDir(){
        val list by fontList.collectAsState()
        Text("List of files found in fonts/ (${list.size}): ")
        for(file in list){
            Text("Font File Found: ${file.path}")
        }
    }


    @Composable
    fun filesExist(){
        val resMap by filesExistList.collectAsState()
        for(entry in resMap.entries){
            Text("${entry.key.baseName} exists: ${entry.value}")
        }
    }
}