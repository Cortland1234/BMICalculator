package edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm

import android.content.Context
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
import android.content.SharedPreferences
import androidx.activity.viewModels
import edu.arizona.cast.cortlanddiehm.bmicalculator_cortlanddiehm.databinding.ActivityMainBinding
import org.w3c.dom.Text

private const val TAG = "SecondActivity"


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val HRViewModel: HRViewModel by viewModels()//Initiating viewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?2) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_second)


        val hr_calculate = findViewById<Button>(R.id.hr_calculate);
        val hr_clear = findViewById<Button>(R.id.hr_clear);
        val max_text = findViewById<TextView>(R.id.max_invis);
        val target_text = findViewById<TextView>(R.id.target_invis);
        val max_HR = findViewById<TextView>(R.id.max_hr);
        val target_HR = findViewById<TextView>(R.id.target);
        val editText = findViewById<EditText>(R.id.age_et);


        loadData()

        hr_calculate.setOnClickListener() {  //This is the method that controls the hr_calculate button. Once clicked, the HR is calculated through HRViewModel's method
            Log.d(TAG, "hr_calculate called")
            saveData()
            val maxHeartrate = HRViewModel.maxHR(getAge())
            val targetHeartrate = HRViewModel.targetHR(maxHeartrate)

            if (maxHeartrate != null && targetHeartrate != null) {
                max_text.setText("Maximum: ")
                max_HR.setText(maxHeartrate.toString())
                target_text.setText("Target: ")
                target_HR.setText(targetHeartrate)


            } else {
                println("ERROR")
            }
        }

        hr_clear.setOnClickListener() { //This method controls the clear button.
            Log.d(TAG, "hr_clear called")
            saveData()

            max_text.text = ""
            max_HR.text = ""
            target_text.text = ""
            target_HR.text = ""
        }
    }


    private fun saveData() { //This function saves the input from the edittext field via SavedPreferences
        val editText = findViewById<EditText>(R.id.age_et);
        val edt = editText.text.toString()
        editText.setText(edt)

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("EDITTEXT", edt)
        }.apply()
    }

    private fun loadData() { //This function takes the input from the edittext from saveData() and sets the most recent user input to the edittext
        val editText = findViewById<EditText>(R.id.age_et);
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("EDITTEXT", null)
        if (savedString != null) {
            editText.setText(savedString)
        }
        else
        {
            editText.setText("")
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


    fun getAge(): Int { //This method controls the input for the age field. If the field is 0 or less, an error is returned.
        val age = 0

        val y = findViewById<EditText>(R.id.age_et);
        val ymsg = y.text.toString().toInt();

        println(ymsg)

        if (ymsg > 0)
            run {
                println(ymsg)
                return ymsg
            }
        else
            run {
                println("ERROR")
                return age.toInt()
            }
    }
}