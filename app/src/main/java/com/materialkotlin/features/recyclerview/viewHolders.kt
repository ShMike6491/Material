package com.materialkotlin.features.recyclerview

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.materialkotlin.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(num: Int) {
        itemView.findViewById<TextView>(R.id.tv_item).text = num.toString()
    }
}

class SecondHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(num: Int) {
        fun rnd(): Int = (0..255).random()
        itemView.setBackgroundColor(Color.argb(255, rnd(), rnd(), rnd()))
        itemView.findViewById<TextView>(R.id.tv_item).text = num.toString()
    }
}