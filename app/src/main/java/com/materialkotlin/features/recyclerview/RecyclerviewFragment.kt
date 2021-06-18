package com.materialkotlin.features.recyclerview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.materialkotlin.R
import kotlinx.android.synthetic.main.fragment_recyclerview.*

class RecyclerviewFragment : Fragment(R.layout.fragment_recyclerview) {

    private val adapter = Adapter()
    private val touchHelper = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.adapter = adapter
        ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerview)
        fab_new.setOnClickListener { adapter.addNew() }
    }
}