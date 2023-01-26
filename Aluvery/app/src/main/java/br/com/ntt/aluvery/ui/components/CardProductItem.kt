package br.com.ntt.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ntt.aluvery.R
import br.com.ntt.aluvery.extensions.toBrazilianCurrency
import br.com.ntt.aluvery.model.Product
import br.com.ntt.aluvery.sampledata.sampleProduct
import coil.compose.AsyncImage

@Composable
fun CardProductItem(product: Product, padding: Modifier){

    Card(
        padding
            .fillMaxWidth()
            .heightIn(150.dp), elevation = 4.dp) {
        Column() {
            AsyncImage(model = product.image, contentDescription = "Uma imagem do produto",
                Modifier
                    .fillMaxWidth()
                    .height(100.dp), placeholder = painterResource(
                id = R.drawable.placeholder_view_vector_svg
            ), contentScale = ContentScale.Crop)
            Column(
                Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.primaryVariant
                            )
                        )
                    )
                    .padding(16.dp)
                    .fillMaxWidth()) {
                Text(text = product.name)
                Text(text = product.price.toBrazilianCurrency())
            }
            product.description?.let{
                Text(text = product.description,  Modifier.padding(16.dp), maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun CardProductItemPreview(){
    CardProductItem(product = sampleProduct, padding = Modifier.padding(horizontal = 16.dp))
}
