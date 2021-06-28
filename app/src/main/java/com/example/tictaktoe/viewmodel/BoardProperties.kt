package com.example.tictaktoe.viewmodel

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.tictaktoe.R

data class BoardProperties(@StringRes var itemText: Int = R.string.NA,
                           @ColorRes var itemBackgroundColor: Int = R.color.board_item_default
)
