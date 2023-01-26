package br.com.ntt.aluvery.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ntt.aluvery.model.Product
import java.math.BigDecimal
import br.com.ntt.aluvery.R
import br.com.ntt.aluvery.sampledata.sampleCandies


@Composable
fun ProductsSection(title: String, products: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = title,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(
                    top = 8.dp,
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {

                items(products){
                    p -> ProductItem(product = p, modifier.padding(bottom = 4.dp))
                }


        }
    }
}

@Preview(name = "ProductSection", showBackground = true)
@Composable
fun ProductSectionPreview(){
    ProductsSection("Promoção", sampleCandies)
}