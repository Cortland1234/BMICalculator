package edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.activity.viewModels
import edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm.databinding.ActivityMainBinding


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val BMIViewModel: BMIViewModel by viewModels() //Initiating viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn_calc = findViewById<Button>(R.id.calculate);
        val bmi_input = findViewById<TextView>(R.id.BMIresult);
        val btn_clear = findViewById<Button>(R.id.clear);
        val healthMessage = findViewById<TextView>(R.id.healthMessage);
        val bmiMessage = findViewById<TextView>(R.id.BMIMessage);
        val hr_button = findViewById<Button>(R.id.heartrate);


        btn_calc.setOnClickListener() {  //This is the method that controls the calculate button. Once clicked, the BMI is calculated through BMIViewModel's method
            Log.d(TAG, "calculate called")
            val bmi = BMIViewModel.calculateBMI(getInches(), getWeight())
            bmiMessage.setText("Your BMI is:")
            bmi_input.setText(bmi)

            val bmiFloat = bmi?.toFloat()

            if (bmiFloat != null) { //These if statements control whether the BMI is considered healthy or unhealthy.
                if (bmiFloat < 18.5) {
                    healthMessage.setText("Underweight")
                    healthMessage.setTextColor(Color.parseColor("#FF03DAC5"))
                }
            }
            if (bmiFloat != null) {
                if (bmiFloat < 24.9 && bmiFloat > 18.6) {
                    healthMessage.setText("Normal")
                    healthMessage.setTextColor(Color.parseColor("#00C914"))
                }
            }
            if (bmiFloat != null) {
                if (bmiFloat < 30 && bmiFloat > 25) {
                    healthMessage.setText("Overweight")
                    healthMessage.setTextColor(Color.parseColor("#FFFF28"))
                }
            }
            if (bmiFloat != null) {
                if (bmiFloat > 30) {
                    healthMessage.setText("Obese")
                    healthMessage.setTextColor(Color.parseColor("#F20008"))
                }
            }
        }

        btn_clear.setOnClickListener() { //This method controls the clear button. Once clicked, all relevant Textviews and EditTexts are wiped of input.
            Log.d(TAG, "clear called")

            val inches = findViewById<EditText>(R.id.inchesedittext);
            val weight = findViewById<EditText>(R.id.weightedittext);

            healthMessage.setText("");
            bmi_input.setText("");
            inches.setText("");
            weight.setText("");
            bmiMessage.setText("");


        }

        hr_button.setOnClickListener() { //This controls the Heartrate cutton. It uses Intent to start the Heartrate calculator activity.
            Log.d(TAG, "hr button called")
            Intent(getApplicationContext(), SecondActivity::class.java).also {
                startActivity(it)
            }

        }



    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    fun getInches(): Float { //This method controls the input for the height field. If the field is 0 or less, an error is returned.
        val inches = 0

        val x = findViewById<EditText>(R.id.inchesedittext);
        val xmsg = x.text.toString().toFloat();

        println(xmsg)

        if (xmsg > 0)
        run {
            println(xmsg)
            return xmsg
        }
        else
        run {
            println("ERROR")
            return inches.toFloat()
        }
    }

    fun getWeight(): Float { //This method controls the input for the weight field. If the field is 0 or less, an error is returned.
        val weight = 0

        val y = findViewById<EditText>(R.id.weightedittext);
        val ymsg = y.text.toString().toFloat();

        println(ymsg)

        if (ymsg > 0)
            run {
                println(ymsg)
                return ymsg
            }
        else
            run {
                println("ERROR")
                return weight.toFloat()
            }
    }
}

