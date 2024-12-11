package com.jetbrains.voyager_navigation.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.voyager_navigation.repository.ProductRepository
import com.jetbrains.voyager_navigation.State
import kotlinx.coroutines.launch

class NewsViewModel : StateScreenModel<State>(State.Init) {

    private val productRepository = ProductRepository()

    init {
        screenModelScope.launch {
            mutableState.value = State.Loading
            productRepository.getTopHeadlines().collect { data ->
                mutableState.value = State.Result(data)
            }
        }
    }
}