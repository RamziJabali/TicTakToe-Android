package com.example.tictaktoe

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView)
        viewModel = ViewModel(this)
        viewModel.startGame()
    }

    fun setNewViewState(newViewState: ViewState, listener: ViewListener) {
        with(newViewState) {
            gridView.numColumns = boardSize
        }
    }

}