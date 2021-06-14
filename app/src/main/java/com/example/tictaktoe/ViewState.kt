package com.example.tictaktoe

class ViewState {
    lateinit var board: Array<Array<String>>
    lateinit var oneDimensionalBoard: Array<String>

    var boardSize: Int = 0
    var gameResultText: String = ""
    var gameAnnouncement: String = ""
}