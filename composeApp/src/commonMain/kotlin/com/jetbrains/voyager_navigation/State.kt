package com.jetbrains.voyager_navigation

import com.jetbrains.voyager_navigation.data.Product

sealed class State {
    object Init : State()
    object Loading : State()
    data class Result(val data: Any) : State()
}