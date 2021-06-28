package com.example.tictaktoe

import com.example.tictaktoe.viewmodel.Player.*
import com.example.tictaktoe.viewmodel.BoardProperties
import com.example.tictaktoe.viewmodel.Coordinate
import com.example.tictaktoe.view.MainActivity
import com.example.tictaktoe.viewmodel.ViewModel
import org.junit.Before
import org.junit.Test
import io.mockk.*
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelTest {

    lateinit var viewModel: ViewModel
    lateinit var mainActivity: MainActivity

    @Before
    fun setup() {
        mainActivity = mockk<MainActivity>(relaxed = true)
        viewModel = ViewModel(mainActivity)
        every { mainActivity.getString(any()) } returns "hello world"
        viewModel.startGame()

    }

    @Test
    fun isBoardFullTest() {
        for (position in 0..8) {
            if (position == 6) {
                viewModel.userClickedPosition(7) //x
                viewModel.userClickedPosition(8) //o
            }
            viewModel.userClickedPosition(position)
        }
        assertTrue(viewModel.isBoardFull())
    }

    @Test
    fun doesUserWinDiagonallyToTheRightTest() {
        for (position in 0 until 6) {
            viewModel.userClickedPosition(position)
        }
        viewModel.userClickedPosition(8)
        assertTrue(viewModel.doesUserWinDiagonally().didUserWin)
    }
    @Test
    fun doesUserWinDiagonallyToTheLeftTest() {
        for (position in 0..8) {
            viewModel.userClickedPosition(position)
        }
        assertTrue(viewModel.doesUserWinDiagonally().didUserWin)
    }

    @Test
    fun doesUserWinHorizontallyTest() {
        for (position in 0..2) {
            viewModel.userClickedPosition(position)
            viewModel.userClickedPosition(position + 3)
        }
        assertTrue(viewModel.doesUserWinHorizontally().didUserWin)
    }

    @Test
    fun doesUserWinVerticallyTest() {
        for (position in 0..6 step 3) {
            viewModel.userClickedPosition(position) //x 0, 3, 6
            viewModel.userClickedPosition(position + 1)// o 1, 4, 7
        }
        assertTrue(viewModel.doesUserWinVertically().didUserWin)
    }

    @Test
    fun convertTwoDimensionalGameBoardToOneDimensionalStringArrayTest() {
        for (position in 0..8) {
            viewModel.userClickedPosition(position)
        }
        val gameBoardProperties: ArrayList<BoardProperties> = ArrayList()
        gameBoardProperties.add(
            BoardProperties(
                X.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                O.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                X.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                O.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                X.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                O.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                X.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                NA.display,
                R.color.board_item_default
            )
        )
        gameBoardProperties.add(
            BoardProperties(
                NA.display,
                R.color.board_item_default
            )
        )
        assertEquals(gameBoardProperties,viewModel.convertTwoDimensionalGameBoardToOneDimensionalStringArray())
    }

    @Test
    fun getXFromPositionTest(){
        assertEquals(1,viewModel.getXFromPosition(3))
    }
    @Test
    fun getYFromPositionTest(){
        assertEquals(2,viewModel.getYFromPosition(5))
    }
    @Test
    fun getPositionFromAndYTest(){
        val coordinate = Coordinate(1, 2)
        assertEquals(5,viewModel.getPositionFromXAndY(coordinate))
    }


}

