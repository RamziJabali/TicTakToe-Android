package com.example.tictaktoe

import android.content.Context

import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView


class TextViewAdapter(var c: Context) : BaseAdapter() {

    private var context = c

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var textView: TextView

        if (convertView == null) {
            textView = TextView(this.context)
            textView.layoutParams = AbsListView.LayoutParams(90, 90)
            textView.setPadding(8, 8, 8, 8)
        } else {
            textView = TextView(context)
        }
        textView.setText(arrayOfSymbols[position])
        return textView

    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return arrayOfSymbols.size
    }

    var arrayOfSymbols = arrayOf("X", "O", "X", "X", "X", "O", "O", "X", "O")
}