package com.example.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.portfolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.image1)
        add(R.drawable.image2)
        add(R.drawable.image3)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewpager()
        initToolbar()
    }

    private fun initToolbar(){
        val toolBar: androidx.appcompat.widget.Toolbar = binding.toolBar
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    
}