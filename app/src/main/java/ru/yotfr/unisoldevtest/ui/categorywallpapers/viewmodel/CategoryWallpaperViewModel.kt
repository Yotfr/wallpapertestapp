package ru.yotfr.unisoldevtest.ui.categorywallpapers.viewmodel

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
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.usecase.GetWallpaperByCategoryUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CategoryWallpaperViewModel @Inject constructor(
    private val getWallpaperByCategoryUseCase: GetWallpaperByCategoryUseCase
) : ViewModel() {

    private val triggerRefresh = MutableStateFlow(false)

    private val category = MutableStateFlow<Category?>(null)

    val wallpapers = combine(
        category, triggerRefresh
    ) { category, refresh ->
        Pair(category, refresh)
    }.flatMapLatest{
        (category, _) ->
        category?.let {
            getWallpaperByCategoryUseCase(it)
        } ?: flow { }
    }

    fun setCategory(value: Category) {
        category.value = value
    }

    fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }

}