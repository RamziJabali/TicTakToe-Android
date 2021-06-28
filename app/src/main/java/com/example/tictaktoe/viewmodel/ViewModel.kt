package com.example.tictaktoe.viewmodel

import android.view.View
import androidx.annotation.VisibleForTesting
import com.example.tictaktoe.R
import com.example.tictaktoe.model.Model.Companion.BOARD_SIZE
import com.example.tictaktoe.viewmodel.Player.*
import com.example.tictaktoe.model.Model
import com.example.tictaktoe.view.MainActivity
import com.example.tictaktoe.view.ViewListener

class ViewModel(private val view: MainActivity) : ViewListener {
    private lateinit var viewState: ViewState
    private lateinit var model: Model

    override fun userClickedButton() {
        startGame()
    }

    override fun userClickedPosition(position: Int) {
        val markCoordinate = Coordinate(getXFromPosition(position), getYFromPosition(position))
        var userWinState: UserWinState = hasPlayerWon()
        with(model) {
            with(markCoordinate) {
                if (gameBoard[x][y] == NA && !userWinState.didUserWin) {
                    gameBoard[x][y] = currentPlayer
                    userWinState = hasPlayerWon()
                    when {
                        userWinState.didUserWin -> showGameOver()
                        isBoardFull() -> showDraw()
                        else -> {
                            currentPlayer = getOppositePlayer()
                        }
                    }
                }
            }
            viewState.gameAnnouncement = view.getString(Model.PLAYER_TURN) + "$currentPlayer \n"
        }
        viewState.oneDimensionalBoard = convertTwoDimensionalGameBoardToOneDimensionalStringArray()
        if (userWinState.didUserWin) {
            for (index in userWinState.arrayListOfPosition) {
                viewState.oneDimensionalBoard[index].itemBackgroundColor = R.color.teal_200
            }
        }
        invalidateView()
    }

    fun startGame() {
        viewState = ViewState()
        model = Model()
        viewState.boardSize = BOARD_SIZE
        viewState.oneDimensionalBoard = convertTwoDimensionalGameBoardToOneDimensionalStringArray()
        showWelcomeMessage()
        invalidateView()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun isBoardFull(): Boolean {
        model.gameBoard.forEach { row ->
            row.forEach { player ->
                if (player == NA) return false
            }
        }
        return true
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun hasPlayerWon(): UserWinState {
        var userWinState: UserWinState = doesUserWinHorizontally()
        if (userWinState.didUserWin) {
            return userWinState
        }
        userWinState = doesUserWinVertically()
        if (userWinState.didUserWin) {
            return userWinState
        }
        return doesUserWinDiagonally()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun doesUserWinHorizontally(): UserWinState {
        var numberOfContiguousMarks = 0
        var userWinState = UserWinState(ArrayList(), false)
        with(model) {
            for (row in gameBoard.indices) {
                for (column in gameBoard.indices) {
                    if (gameBoard[row][column] == currentPlayer) {
                        numberOfContiguousMarks++
                        userWinState.arrayListOfPosition.add(getPositionFromXAndY(Coordinate(row, column)))
                    } else {
                        numberOfContiguousMarks = 0
                        userWinState.arrayListOfPosition = ArrayList()
                    }
                }
                if (numberOfContiguousMarks >= BOARD_SIZE) {
                    userWinState.didUserWin = true
                    return userWinState
                } else {
                    numberOfContiguousMarks = 0
                    userWinState.arrayListOfPosition = ArrayList()
                }
            }
        }
        userWinState.arrayListOfPosition = ArrayList()
        userWinState.didUserWin = false
        return userWinState
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun doesUserWinVertically(): UserWinState {
        var numberOfContiguousMarks = 0
        var userWinState = UserWinState(ArrayList(), false)
        with(model) {
            for (column in model.gameBoard.indices) {
                for (row in model.gameBoard.indices) {
                    if (gameBoard[row][column] == currentPlayer) {
                        numberOfContiguousMarks++
                        userWinState.arrayListOfPosition.add(getPositionFromXAndY(Coordinate(row, column)))
                    } else {
                        numberOfContiguousMarks = 0
                        userWinState.arrayListOfPosition = ArrayList()
                    }
                }
                if (numberOfContiguousMarks >= BOARD_SIZE) {
                    userWinState.didUserWin = true
                    return userWinState
                }
                numberOfContiguousMarks = 0
                userWinState.arrayListOfPosition = ArrayList()
            }
        }
        userWinState.arrayListOfPosition = ArrayList()
        userWinState.didUserWin = false
        return userWinState
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun doesUserWinDiagonally(): UserWinState {
        var numberOfContiguousMarks = 0
        var userWinState = UserWinState(ArrayList(), false)
        with(model) {
            for ((row, column) in (0 until BOARD_SIZE).zip(0 until BOARD_SIZE)) {
                if (gameBoard[row][column] == currentPlayer) {
                    numberOfContiguousMarks++
                    userWinState.arrayListOfPosition.add(getPositionFromXAndY(Coordinate(row, column)))
                } else {
                    numberOfContiguousMarks = 0
                    userWinState.arrayListOfPosition = ArrayList()
                }
            }
            if (numberOfContiguousMarks >= BOARD_SIZE) {
                userWinState.didUserWin = true
                return userWinState
            }

            for ((row, column) in (0 until BOARD_SIZE).zip(BOARD_SIZE - 1 downTo 0)) {
                if (gameBoard[row][column] == currentPlayer) {
                    numberOfContiguousMarks++
                    userWinState.arrayListOfPosition.add(getPositionFromXAndY(Coordinate(row, column)))
                } else {
                    numberOfContiguousMarks = 0
                    userWinState.arrayListOfPosition = ArrayList()
                }
            }
            if (numberOfContiguousMarks >= BOARD_SIZE) {
                userWinState.didUserWin = true
                return userWinState
            }
        }
        userWinState.arrayListOfPosition = ArrayList()
        userWinState.didUserWin = false
        return userWinState
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun convertTwoDimensionalGameBoardToOneDimensionalStringArray(): ArrayList<BoardProperties> {
        var gameBoardProperties: ArrayList<BoardProperties> = ArrayList()
        for (row in model.gameBoard) {
            for (column in row) {
                gameBoardProperties.add(BoardProperties(column.display))
            }
        }
        return gameBoardProperties
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getXFromPosition(position: Int): Int = position / 3

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getYFromPosition(position: Int): Int = position - (getXFromPosition(position) * BOARD_SIZE)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getPositionFromXAndY(coordinate: Coordinate): Int = coordinate.y + (coordinate.x * BOARD_SIZE)

    private fun showWelcomeMessage() {
        viewState.gameAnnouncement =
                view.getString(Model.WELCOME_TO_TICK) + "\n" + view.getString(Model.YOU_ARE_PLAYER) + "$X \n"
    }

    private fun showGameOver() {
        viewState.gameResultText = "${view.getString(Model.YOU_WIN)} ${view.getString(Model.PLAYER)}: ${model.currentPlayer} "
        viewState.isGameOver = View.VISIBLE
    }

    private fun showDraw() {
        viewState.gameResultText = view.getString(Model.ITS_A_DRAW)
        viewState.isGameOver = View.VISIBLE
    }

    private fun invalidateView() {
        view.setNewViewState(viewState)
    }
}
