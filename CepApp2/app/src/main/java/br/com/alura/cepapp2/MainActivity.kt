package br.com.alura.cepapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.cepapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}