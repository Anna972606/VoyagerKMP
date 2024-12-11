package com.jetbrains.voyager_navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.jetbrains.voyager_navigation.viewmodel.HomeScreenModel
import com.jetbrains.voyager_navigation.State
import com.jetbrains.voyager_navigation.data.Product
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch

class ListScreen : Screen {

    @Composable
    override fun Content() {
        val homeScreenModel = rememberScreenModel { HomeScreenModel() }
        val state by homeScreenModel.state.collectAsState()

        when (val result =state) {
            is State.Loading -> LoadingContent()
            is State.Result ->( result.data as? List<Product>)?.let { ProductContent(it) }
            else ->{}
        }

    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun ProductContent(products: List<Product>) {
        BottomSheetNavigator {

            val bottomSheetNavigator = LocalBottomSheetNavigator.current
            BoxWithConstraints {
                val scope = this
                val maxWidth = scope.maxWidth

                var cols = 2
                if (maxWidth > 840.dp) {
                    cols = 3
                }

                val scrollState = rememberLazyGridState()
                val coroutineScope = rememberCoroutineScope()

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(cols),
                        state = scrollState,
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.draggable(
                            orientation = Orientation.Vertical,
                            state = rememberDraggableState { delta ->
                                coroutineScope.launch {
                                    scrollState.scrollBy(-delta)
                                }
                            })
                    ) {

                        items(
                            items = products,
                            key = { product -> product.id.toString() }) { product ->

                            Card(
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        bottomSheetNavigator.show(ProductDetailScreen(product) {
                                            bottomSheetNavigator.hide()
                                        })
                                    },
                                elevation = 2.dp
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val painter =
                                        rememberImagePainter(url = product.image.toString())
                                    Image(
                                        painter,
                                        modifier = Modifier.height(130.dp).padding(8.dp),
                                        contentDescription = product.title
                                    )
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            product.title.toString(),
                                            textAlign = TextAlign.Start,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(horizontal = 16.dp)
                                                .heightIn(min = 40.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            "${product.price.toString()} INR ",
                                            textAlign = TextAlign.Start,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.wrapContentWidth()
                                                .padding(horizontal = 16.dp).heightIn(min = 40.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
