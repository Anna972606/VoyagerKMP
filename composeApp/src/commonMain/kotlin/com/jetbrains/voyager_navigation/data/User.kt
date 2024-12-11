package com.jetbrains.voyager_navigation.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("date")
    val date: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("products")
    val products: List<UserProduct>?,
    @SerialName("userId")
    val userId: Int?,
    @SerialName("__v")
    val v: Int?
)