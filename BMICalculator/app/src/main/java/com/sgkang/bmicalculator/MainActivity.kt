package com.sgkang.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightEditText: EditText = findViewById(R.id.weight)
        val heightEditText: EditText = findViewById(R.id.height)
        val btn: Button = findViewById(R.id.result)

        btn.setOnClickListener{
            if(weightEditText.text.isEmpty() || heightEditText.text.isEmpty()){
                Toast.makeText(this, "빈 값이다 이기야", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val weight: Int = weightEditText.text.toString().toInt()
            val height: Int = heightEditText.text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}