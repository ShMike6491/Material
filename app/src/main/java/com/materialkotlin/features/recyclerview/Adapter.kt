package com.materialkotlin.features.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.materialkotlin.R
import java.lang.IllegalArgumentException
import java.util.*

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf(1)

    override fun getItemViewType(position: Int): Int {
        return when {
            data[position] >= 0 -> VIEW_TYPE_SECOND
            data[position] < 0 -> VIEW_TYPE_HOLDER
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_SECOND ->
                SecondHolder(
                    inflater.inflate(R.layout.item_second_holder, parent, false) as View
                )
            VIEW_TYPE_HOLDER -> ViewHolder(
                inflater.inflate(R.layout.item_view_holder, parent, false) as View
            )
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) holder.bind((data[position]))
        if (holder is SecondHolder) holder.bind((data[position]))
    }

    override fun getItemCount() = data.size //+ 1

    fun addNew() {
        data.add((-10..10).random())
        notifyItemInserted(itemCount - 1)
    }

    fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    companion object {
        const val VIEW_TYPE_HOLDER = 1
        const val VIEW_TYPE_SECOND = 2
    }
}