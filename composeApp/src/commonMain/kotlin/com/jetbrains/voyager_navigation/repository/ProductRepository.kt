package com.jetbrains.voyager_navigation.repository

import com.jetbrains.voyager_navigation.apiClient.httpClient
import com.jetbrains.voyager_navigation.data.NewsResponse
import com.jetbrains.voyager_navigation.data.Product
import com.jetbrains.voyager_navigation.data.User
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class ProductRepository {

    private suspend fun getProductsApi(): List<Product> {
        val response = httpClient.get("https://fakestoreapi.com/products")
        return response.body()
    }

    private suspend fun getUsersApi(): List<User> {
        val response = httpClient.get("https://fakestoreapi.com/carts?userId=1")
        return response.body()
    }

    private suspend fun getTopHeadlinesApi(): NewsResponse {
        val response = httpClient.get("https://newsapi.org/v2/top-headlines") {
            url {
                parameters.append("apiKey", "62f9c72b89f34eaf97679bbc58be09e1")
                parameters.append("country", "US")
            }
        }
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }

    fun getUsers() = flow {
        emit(getUsersApi())
    }

    fun getTopHeadlines() = flow {
//        emit(getTopHeadlinesApi().articles)
        emit(getTopHeadlinesApi())
    }
}