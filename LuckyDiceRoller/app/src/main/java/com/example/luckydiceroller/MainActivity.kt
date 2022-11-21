package com.example.luckydiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    fun rollDice() {
        var selectedLuckyNum = 4
        var dice = Dice(6)
        val textView: TextView = findViewById(R.id.textView)
        val textLuckyNumber: TextView = findViewById(R.id.textView3)
        textLuckyNumber.text = selectedLuckyNum.toString()
        val result = dice.roll()
        textView.text = result.toString()
        Toast.makeText(
            this,
            "${if (selectedLuckyNum == result) "You're Lucky!" else "You're not Lucky"} ",
            Toast.LENGTH_SHORT
        ).show()


    }
}

class Dice(val numSides: Int){

    fun roll(): Int{
        return (1..numSides).random()
    }

}