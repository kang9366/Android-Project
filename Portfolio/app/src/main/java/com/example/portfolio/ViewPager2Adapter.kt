package com.example.portfolio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ViewPager2Adapter(
    private val context: Context,
    private val imageList: MutableList<Int>
): RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.viewholder,
            parent,
            false
        )
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
    }

    override fun getItemCount(): Int = imageList.size
}