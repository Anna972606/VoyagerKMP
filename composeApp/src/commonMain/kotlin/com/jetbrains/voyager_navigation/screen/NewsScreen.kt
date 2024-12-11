package com.jetbrains.voyager_navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.voyager_navigation.viewmodel.NewsViewModel
import com.jetbrains.voyager_navigation.State
import com.jetbrains.voyager_navigation.data.Article
import com.jetbrains.voyager_navigation.data.NewsResponse
import com.seiko.imageloader.rememberImagePainter

class NewsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { NewsViewModel() }
        val state by viewModel.state.collectAsState()
        when (val result =state) {
            is State.Loading -> LoadingContent()
            is State.Result ->(result.data as? NewsResponse)?.let { NewsUI(it.articles) }
            else ->{}
        }
    }

    @Composable
    fun NewsUI(articles: List<Article>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = articles
            ) { index, item ->
                ItemNews(item)
                if (index < articles.lastIndex) {
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
            }
        }
    }

    @Composable
    private fun ItemNews(item: Article) {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    navigator.push(NewsDetailScreen(item))
                    // Also works:
//                     navigator push NewsDetailScreen(item)
//                     navigator += NewsDetailScreen(item)
                }
        ) {

            val painter = rememberImagePainter(url = item.urlToImage.orEmpty())
            Image(
                painter,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentDescription = null
            )
            Text(
                text = "Name : ${item.title}",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Author: ${item.author}",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Title: ${item.title}",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Description: ${item.description}",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Published At: ${item.publishedAt}",
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}