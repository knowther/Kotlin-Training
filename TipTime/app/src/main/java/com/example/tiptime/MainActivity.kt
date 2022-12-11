package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myButton: Button = binding.calculateButton

        myButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip(){
        val  stringTextinField = binding.costOfServiceEditText.text.toString()
        val cost = stringTextinField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = "Insert a value on tip field"
            return
        }

        val tipPercentage = when(binding.buttonGroup.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)
       val formatedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatedTip)
    }
}