package com.example.tictaktoe.model


import com.example.tictaktoe.viewmodel.Player
import com.example.tictaktoe.viewmodel.Player.*
import com.example.tictaktoe.R

class Model {
    companion object {
        const val WELCOME_TO_TICK =
            R.string.WELCOME_TO_TICK
        const val YOU_ARE_PLAYER = R.string.YOU_ARE_PLAYER
        const val YOU_WIN = R.string.YOU_WIN
        const val PLAYER = R.string.PLAYER
        const val PLAYER_TURN = R.string.PLAYER_TURN
        const val ITS_A_DRAW = R.string.ITS_A_DRAW
        const val BOARD_SIZE = 3
    }

    var currentPlayer: Player = X
    var gameBoard = arrayOf(
            arrayOf(NA, NA, NA),
            arrayOf(NA, NA, NA),
            arrayOf(NA, NA, NA))

    fun getOppositePlayer(): Player = if (currentPlayer == X) O else X

}