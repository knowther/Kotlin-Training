package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        val inputCost = binding.costOfServiceEditText

        inputCost.setOnKeyListener { view, keyCode, _ ->
            handleKayEvent(view, keyCode)
        }

        myButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun handleKayEvent(view: View, keyCode: Int): Boolean{

        if(keyCode == KeyEvent.KEYCODE_ENTER){
            //hiding keyboard

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
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