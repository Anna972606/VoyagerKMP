package com.jetbrains.voyager_navigation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.jetbrains.voyager_navigation.data.Product
import com.jetbrains.voyager_navigation.tab.ProfileTab

data class ProductDetailScreen(
    val product: Product,
    val onCloseListener: (() -> Unit)
) : Screen {

    @Composable
    override fun Content() {
        val tabNavigator = LocalTabNavigator.current
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onCloseListener.invoke() }
                )
            }
            Text(
                text = "Product title: ${product.title}",
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = "Product Desc: ${product.description}",
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp),
                onClick = { tabNavigator.current = ProfileTab}
            ) {
                Text(
                    text = "Go to Profile Tab",
                    modifier = Modifier
                        .padding(all = 8.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
        }
    }
}