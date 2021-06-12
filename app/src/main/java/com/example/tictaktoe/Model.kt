package com.example.tictaktoe


import com.example.tictaktoe.Player.*
import java.lang.Math.random

class Model {
    companion object {
        const val WELCOME_TO_TICK = "Welcome to Tic Tac Toe"
        const val YOU_ARE_PLAYER = "You are player "
        const val AI_TURN = "AI turn: "
        const val HUMAN_TURN = "Human turn: "
        const val ENTER_ROW = "Enter row number"
        const val ENTER_COLUMN = "Enter column number"
        const val YOU_WIN = "YOU WIN: "
        const val PLAYER = "PLAYER "
        const val ITS_A_DRAW = "IT'S A DRAW"
        const val BOARD_SIZE = 3
    }

    var userRowEntry: Int = -1
    var userColumnEntry: Int = -1
    var currentPlayer: Player = X
    var aiPlayerPick: Player = NA
    var humanPlayerPick: Player = NA
    var gameBoard = arrayOf(
            arrayOf(NA, NA, NA),
            arrayOf(NA, NA, NA),
            arrayOf(NA, NA, NA)
    )



}