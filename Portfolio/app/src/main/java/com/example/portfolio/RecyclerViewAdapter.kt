package com.example.portfolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerViewAdapter(private val items: ArrayList<RecyclerViewData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageDrawable(item.image)
        holder.name.text = holder.name.toString()
        holder.period.text = holder.period.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(inflatedView)
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        val image: ImageView = view.findViewById(R.id.activityIcon)
        val name: TextView = view.findViewById(R.id.activityName)
        val period: TextView = view.findViewById(R.id.activityPeriod)
    }
}