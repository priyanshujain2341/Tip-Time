package com.polymath.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.polymath.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDouble()
        val tipPercentageButton = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(tipPercentageButton)
        {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tipAmount = tipPercentage * cost

        val switchValue = binding.roundTipSwitch.isChecked()

        if(switchValue)
        {
            tipAmount = kotlin.math.ceil(tipAmount)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}