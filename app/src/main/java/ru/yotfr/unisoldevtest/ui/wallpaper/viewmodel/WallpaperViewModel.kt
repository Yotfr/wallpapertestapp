package ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.usecase.GetWallpaperByIdUseCase
import ru.yotfr.unisoldevtest.ui.wallpaper.state.WallpaperScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperByIdUseCase: GetWallpaperByIdUseCase
) : ViewModel() {

    private val triggerRefresh = MutableStateFlow(false)

    private val _state = MutableStateFlow(WallpaperScreenState())
    val state = _state.asStateFlow()

    private val id = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            combine(
                triggerRefresh,
                id
            ) { trigger, id ->
                Pair(trigger, id)
            }.flatMapLatest { (_, id) ->
                id?.let {
                    getWallpaperByIdUseCase(it)
                } ?: flow {  }
            }.collectLatest { response ->
                when(response) {
                    is MResponse.Exception -> {
                        // TODO: Error state
                    }
                    is MResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is MResponse.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                wallpaper = response.data
                            )
                        }
                    }
                }
            }
        }
    }
    fun changeId(value: String) {
        id.value = value
    }

    fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }

}