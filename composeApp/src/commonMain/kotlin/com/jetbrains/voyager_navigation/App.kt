package com.jetbrains.voyager_navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.jetbrains.voyager_navigation.tab.HomeTab
import com.jetbrains.voyager_navigation.tab.NewsTab
import com.jetbrains.voyager_navigation.tab.ProfileTab
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        TabNavigator(HomeTab) {
            Scaffold(
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(NewsTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            )
        }
    }
}