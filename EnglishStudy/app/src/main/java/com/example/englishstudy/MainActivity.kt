package com.example.englishstudy

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.englishstudy.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val floatingButton: FloatingActionButton = binding.floatingButton
        floatingButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("Test")
                .setMessage("Test")
                .setPositiveButton("확인",
                DialogInterface.OnClickListener{ dialog, which ->
                    Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
                })
            builder.show()
        }
    }
}