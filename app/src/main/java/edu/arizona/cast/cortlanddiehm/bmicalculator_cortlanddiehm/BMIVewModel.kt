package edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

private const val TAG = "BMIViewModel"

class BMIViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    fun calculateBMI(num1:Float, num2:Float): String? { //This method takes two floats (from getInches() and getWeigh()) and calculates the BMI. The result is then formatted to one decimal point.

        val dec = DecimalFormat("##.0")
        val bmi = (num2 * 703) / (num1 * num1)
        dec.roundingMode = RoundingMode.CEILING


        return dec.format(bmi)
    }
}