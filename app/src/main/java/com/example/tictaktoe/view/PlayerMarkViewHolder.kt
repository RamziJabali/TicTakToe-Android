package com.example.tictaktoe.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tictaktoe.viewmodel.BoardProperties
import com.example.tictaktoe.R

class PlayerMarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val playerMarkTextView: TextView = itemView.findViewById(R.id.playerCharacter)

    companion object {
        fun new(context: Context, parent: ViewGroup?): PlayerMarkViewHolder =
            PlayerMarkViewHolder(
                LayoutInflater.from(context)
                    .inflate(
                        R.layout.item_player_mark,
                        parent,
                        false
                    )
            )
    }

    fun bind(boardProperties: BoardProperties) {
        val context = playerMarkTextView.context
//        playerMarkTextView.text = context.getString(boardProperties.itemText)
        playerMarkTextView.setText(boardProperties.itemText)

//        playerMarkTextView.setBackgroundColor(context.getColor(boardProperties.itemBackgroundColor))
        itemView.setBackgroundResource(boardProperties.itemBackgroundColor)
        itemView.invalidate()
    }

}