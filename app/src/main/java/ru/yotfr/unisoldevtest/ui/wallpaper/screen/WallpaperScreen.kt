package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel.WallpaperViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WallpaperScreen(
    vm: WallpaperViewModel = hiltViewModel(),
    id: String
) {

    val state by vm.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { vm.refresh() }
    )

    LaunchedEffect(Unit) {
        vm.changeId(id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        AsyncImage(
            model = state.wallpaper?.url ?: "",
            contentDescription = null
        )
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(text = "WL")
        }
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "L")
        }
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(text = "W")
        }
        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState
        )
    }


}