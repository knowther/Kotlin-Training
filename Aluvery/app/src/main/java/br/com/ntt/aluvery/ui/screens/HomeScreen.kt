package br.com.ntt.aluvery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ntt.aluvery.model.Product
import br.com.ntt.aluvery.sampledata.sampleProducts
import br.com.ntt.aluvery.sampledata.sampleSection
import br.com.ntt.aluvery.ui.components.CardProductItem
import br.com.ntt.aluvery.ui.components.ProductsSection
import br.com.ntt.aluvery.ui.components.SearchTextField

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>, searchText: String = ""
){
    Column {
        var text by remember {
            mutableStateOf(searchText)
        }
            SearchTextField(searchText = text, onSearchChange = {
                text = it
            })
        val searchedProducts = remember(text) {
            sampleProducts.filter { product ->
                product.name.contains(text, ignoreCase = true) ||
                        product.description?.contains(text) ?: false
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if(text.isBlank()){
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(title = title, products = products)
                    }

                }
            }else{
                items(searchedProducts){p ->
                    CardProductItem(product = p, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(sampleSection)
}