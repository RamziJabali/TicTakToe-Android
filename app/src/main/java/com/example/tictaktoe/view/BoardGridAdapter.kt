package com.example.tictaktoe.view

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.tictaktoe.viewmodel.BoardProperties

class BoardGridAdapter : BaseAdapter() {

    private var boardProperties: Array<BoardProperties> = emptyArray()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context = parent!!.context
        val viewHolder: PlayerMarkViewHolder =
            PlayerMarkViewHolder.new(
                context,
                parent
            )
        viewHolder.bind(getItem(position))
        return viewHolder.itemView
    }

    override fun getItem(position: Int): BoardProperties = boardProperties[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = boardProperties.size

    fun setBoardItems(items: ArrayList<BoardProperties>) {
        boardProperties = items.toTypedArray()
        notifyDataSetChanged()
    }
}