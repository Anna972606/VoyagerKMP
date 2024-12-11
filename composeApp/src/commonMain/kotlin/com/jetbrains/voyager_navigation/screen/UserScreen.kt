package com.jetbrains.voyager_navigation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.voyager_navigation.viewmodel.ProfileViewModel
import com.jetbrains.voyager_navigation.State
import com.jetbrains.voyager_navigation.data.User

class UserScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ProfileViewModel() }
        val state by viewModel.state.collectAsState()

        when (val result = state) {
            is State.Loading -> LoadingContent()
            is State.Result ->(result.data as? List<User>)?.let { ProfileContent(it) }
            else ->{}
        }
    }

    @Composable
    private fun ProfileContent(user: List<User>) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {

            LazyColumn {

                items(
                    items = user,
                    key = { product -> product.id.toString() }) { product ->

                    Card(
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        elevation = 2.dp
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person, contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "User ID : XXX-${product.userId}",
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Number product: ${product.products?.size}",
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Text(
                                    text = product.date.orEmpty(),
                                    textAlign = TextAlign.Start,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
