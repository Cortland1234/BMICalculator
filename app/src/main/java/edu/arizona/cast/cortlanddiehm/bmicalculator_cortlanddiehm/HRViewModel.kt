package edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

private const val TAG = "HRViewModel"

class HRViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    fun maxHR (num1: Int): Int { //This method calculates teh max heartrate
        val max = 220 - num1

        return max
    }


    fun targetHR (num1: Int): String { //This method calculates the target heartrate.
        val target_low =  num1 * 0.5
        val target_high =  num1 * 0.85

        val int1 = target_low.roundToInt()
        val int2 = target_high.roundToInt()

        return "$int1 - $int2"
    }


}
