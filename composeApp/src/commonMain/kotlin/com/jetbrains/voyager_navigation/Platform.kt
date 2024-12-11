package com.jetbrains.voyager_navigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform