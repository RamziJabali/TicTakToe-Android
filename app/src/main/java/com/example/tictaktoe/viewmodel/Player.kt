package com.example.tictaktoe.viewmodel

import androidx.annotation.StringRes
import com.example.tictaktoe.R

enum class Player(@StringRes val display: Int) {
    X(R.string.X),
    O(R.string.O),
    NA(R.string.NA);
}