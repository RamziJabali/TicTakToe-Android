package com.example.tictaktoe

import com.example.tictaktoe.Player.*

class ViewModel(private val view: MainActivity) : ViewListener {
    private lateinit var viewState: ViewState
    private lateinit var model: Model


    override fun userClickedCoordinate(coordinate: Coordinate) {
        TODO("Not yet implemented")
    }

    fun startGame() {
        viewState = ViewState()
        model = Model()
        viewState.boardSize = Model.BOARD_SIZE
        viewState.board = getBoardGameAsString()
        showWelcomeMessage()
        invalidateView()
    }


    fun isBoardFull(): Boolean {
        for (row in model.gameBoard) {
            for (column in row) {
                if (column == NA) {
                    return false
                }
            }
        }
        return true
    }

    fun hasPlayerWon(): Boolean =
            doesUserWinDiagonally() || doesUserWinHorizontally() || doesUserWinVertically()

    private fun doesUserWinHorizontally(): Boolean {
        var numberOfContiguousMarks = 0
        for (row in model.gameBoard) {
            for (column in row) {
                if (column == model.currentPlayer) numberOfContiguousMarks++ else numberOfContiguousMarks = 0
            }
            if (numberOfContiguousMarks == Model.BOARD_SIZE) return true
        }
        return false
    }

    private fun doesUserWinVertically(): Boolean {
        var numberOfContiguousMarks = 0
        var row: Int
        var column = 0
        while (column in 0..(Model.BOARD_SIZE - 1)) {
            row = 0
            while (row in 0..(Model.BOARD_SIZE - 1)) {
                if (model.gameBoard[row][column] == model.currentPlayer) numberOfContiguousMarks++ else numberOfContiguousMarks =
                        0
                row++
            }
            if (numberOfContiguousMarks >= Model.BOARD_SIZE) return true
            numberOfContiguousMarks = 0
            column++
        }
        return false
    }

    private fun doesUserWinDiagonally(): Boolean {
        var numberOfContiguousMarks = 0
        var row = 0
        var column = 0

        while (row in 0..(Model.BOARD_SIZE - 1)) {
            if (model.gameBoard[row][column] == model.currentPlayer) numberOfContiguousMarks++ else numberOfContiguousMarks =
                    0
            row++
            column++
        }
        if (numberOfContiguousMarks >= Model.BOARD_SIZE) return true

        numberOfContiguousMarks = 0

        row = 0
        column = 2
        while (row in 0..(Model.BOARD_SIZE - 1)) {
            if (model.gameBoard[row][column] == model.currentPlayer) numberOfContiguousMarks++ else numberOfContiguousMarks =
                    0
            row++
            column--
        }
        if (numberOfContiguousMarks >= Model.BOARD_SIZE) return true

        return false
    }

    private fun getBoardGameAsString(): Array<Array<String>> {
        return emptyArray()
    }

    private fun showWelcomeMessage() {
        viewState.gameAnnouncement =
                Model.WELCOME_TO_TICK + "\n" + Model.YOU_ARE_PLAYER + "${model.humanPlayerPick} \n"
    }

    private fun showGameOver() {
        viewState.gameResultText = "${Model.YOU_WIN} ${Model.PLAYER} ${model.currentPlayer} "
    }

    private fun invalidateView() {
        view.setNewViewState(viewState, this)
    }

}