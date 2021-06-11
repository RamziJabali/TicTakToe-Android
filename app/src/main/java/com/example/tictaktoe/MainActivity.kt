package com.example.tictaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gridView: GridView = findViewById(R.id.gridView)
        gridView.setAdapter(TextViewAdapter(this))

    }
}