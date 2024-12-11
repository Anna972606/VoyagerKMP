package com.jetbrains.voyager_navigation.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProduct(
    @SerialName("productId")
    val productId: Int?,
    @SerialName("quantity")
    val quantity: Int?
)