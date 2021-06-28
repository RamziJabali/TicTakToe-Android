package com.example.tictaktoe.viewmodel

import android.view.View

class ViewState {
    var oneDimensionalBoard: ArrayList<BoardProperties> = ArrayList()
    var boardSize: Int = 0
    var gameResultText: String = ""
    var gameAnnouncement: String = ""
    var isGameOver: Int = View.GONE
}