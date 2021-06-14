package com.example.tictaktoe

import TextViewAdapter
import android.os.Bundle
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictaktoe.Player.*

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var viewModel: ViewModel
    private lateinit var announcementTextView: TextView
    private lateinit var textViewAdapter: TextViewAdapter
    private var stringBoard = emptyArray<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        announcementTextView = findViewById(R.id.announcementTextView)
        textViewAdapter = TextViewAdapter(this, this, stringBoard)
        gridView = findViewById(R.id.gridView)
        gridView.adapter = textViewAdapter
        viewModel = ViewModel(this)
        viewModel.startGame()
    }

    fun setNewViewState(newViewState: ViewState, listener: ViewListener) {
        with(newViewState) {
            gridView.numColumns = boardSize
            announcementTextView.text = gameAnnouncement
            textViewAdapter.stringBoard = oneDimensionalBoard
            gridView.adapter = textViewAdapter
        }
    }

}