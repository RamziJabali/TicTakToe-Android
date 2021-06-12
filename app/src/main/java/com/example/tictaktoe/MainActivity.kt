package com.example.tictaktoe

import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var viewModel: ViewModel
    private lateinit var announcementTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        announcementTextView = findViewById(R.id.announcementTextView)
        gridView = findViewById(R.id.gridView)
        viewModel = ViewModel(this)
        viewModel.startGame()
    }

    fun setNewViewState(newViewState: ViewState, listener: ViewListener) {
        with(newViewState) {
            gridView.numColumns = boardSize
            announcementTextView.text = gameAnnouncement
        }
    }

}