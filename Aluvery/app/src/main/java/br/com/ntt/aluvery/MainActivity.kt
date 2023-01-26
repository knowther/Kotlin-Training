package br.com.ntt.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.ntt.aluvery.sampledata.sampleSection
import br.com.ntt.aluvery.ui.screens.HomeScreen
import br.com.ntt.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
@Composable
fun App(){
    AluveryTheme() {
        Surface {
            HomeScreen(sampleSection)
        }

    }
}
//previews
@Preview(showSystemUi = true)
@Composable
fun AppPreview(){
    App()
}


