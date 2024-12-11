package com.jetbrains.voyager_navigation.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.voyager_navigation.repository.ProductRepository
import com.jetbrains.voyager_navigation.State
import kotlinx.coroutines.launch

class HomeScreenModel : StateScreenModel<State>(State.Init) {

    private val productRepository = ProductRepository()

    init {
        screenModelScope.launch{
            mutableState.value = State.Loading
            productRepository.getProducts().collect { products ->
                mutableState.value = State.Result(products)
            }
        }
    }
}