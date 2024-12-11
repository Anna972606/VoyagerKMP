package com.jetbrains.voyager_navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.jetbrains.voyager_navigation.data.Article
import com.jetbrains.voyager_navigation.tab.ProfileTab
import com.seiko.imageloader.rememberImagePainter

data class NewsDetailScreen(
    val article: Article
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tabNavigator = LocalTabNavigator.current

        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            val painter = rememberImagePainter(url = article.urlToImage.orEmpty())
            Image(
                painter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentDescription = null
            )
            Text(
                text = "Product title: ${article.title}",
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = "Product Desc: ${article.description}",
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = { navigator.pop() }
            ) {
                Text(
                    text = "Go back",
                    modifier = Modifier
                        .padding(all = 8.dp)
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = {
                    tabNavigator.current = ProfileTab
                    navigator.pop()
                }
            ) {
                Text(
                    text = "Go to Profile Tab",
                    modifier = Modifier
                        .padding(all = 8.dp)
                )
            }
        }
    }
}