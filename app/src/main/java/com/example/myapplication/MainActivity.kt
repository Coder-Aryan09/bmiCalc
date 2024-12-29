package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val button = findViewById<AppCompatButton>(R.id.calcButton)

        button.setOnClickListener {
            // Get the values from sliders
            val weight = findViewById<Slider>(R.id.slider1).value
            val height = findViewById<Slider>(R.id.slider2).value

            // Convert height to meters
            val heightInMeter = height / 100

            // BMI formula: BMI = weight (kg) / (height (m)^2)
            val bmi = weight / (heightInMeter * heightInMeter)

            // Display the result
            val answer = findViewById<TextView>(R.id.answer)
            answer.text = bmi.toString()

            val bmiCategory = findViewById<TextView>(R.id.bmiCategory)
            bmiCategory.text = when {
                bmi < 18.5 -> "Underweight :-("
                bmi in 18.5..24.9 -> "Normal Weight (Fit) :-)"
                bmi in 25.0..29.9 -> "Overweight :-("
                else -> "Obese :-{"
            }
        }
    }
}