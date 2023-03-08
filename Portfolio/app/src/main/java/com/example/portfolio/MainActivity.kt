package com.example.portfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.portfolio.databinding.ActivityMainBinding
import me.relex.circleindicator.CircleIndicator3

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
        initRecyclerView()
    }

    private fun initToolbar(){
        val toolBar: androidx.appcompat.widget.Toolbar = binding.toolBar
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun initViewpager(){
        val circleIndicator: CircleIndicator3 = binding.circleIndicator
        val viewPager: ViewPager2 = binding.viewPager

        circleIndicator.apply {
            setViewPager(viewPager)
            createIndicators(imageList.size, 0)
        }

        viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            adapter = ViewPager2Adapter(this@MainActivity, imageList)

            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    circleIndicator.animatePageSelected(position%imageList.size)
                }
            })
            setPageTransformer(MarginPageTransformer(100))
            setPadding(100, 0, 100, 0)
        }
    }

    private fun initRecyclerView(){
        val recyclerView = binding.recyclerView
        val item = ArrayList<RecyclerViewData>()

        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))
        item.add(RecyclerViewData(getDrawable(R.drawable.ic_club)!!, "GDSC", "2022.09 ~ 2023.08"))

        val adapter = RecyclerViewAdapter(item)
        recyclerView.adapter = adapter
    }
}