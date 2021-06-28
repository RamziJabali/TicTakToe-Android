package com.example.tictaktoe.view

import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictaktoe.R
import com.example.tictaktoe.viewmodel.ViewModel
import com.example.tictaktoe.viewmodel.ViewState

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var viewModel: ViewModel
    private lateinit var announcementTextView: TextView
    private lateinit var gameResultTextView: TextView
    private lateinit var gameResetButton: Button
    private lateinit var boardGridAdapter: BoardGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        viewModel.startGame()
    }

    private fun setUpView() {
        announcementTextView = findViewById(R.id.announcementTextView)
        gameResultTextView = findViewById(R.id.gameResultTextView)
        gameResetButton = findViewById(R.id.gameResetButton)
        boardGridAdapter = BoardGridAdapter()
        gridView = findViewById(R.id.gridView)
        viewModel = ViewModel(this)
        gridView.setOnItemClickListener { parent, view, position, id ->
            viewModel.userClickedPosition(position)
        }
        gridView.adapter = boardGridAdapter

        gameResetButton.setOnClickListener {
            viewModel.userClickedButton()
        }
    }

    fun setNewViewState(newViewState: ViewState) {
        with(newViewState) {
            gridView.numColumns = boardSize
            announcementTextView.text = gameAnnouncement
            gameResultTextView.text = gameResultText
            gameResetButton.visibility = isGameOver
            boardGridAdapter.setBoardItems(oneDimensionalBoard)
        }
    }

}