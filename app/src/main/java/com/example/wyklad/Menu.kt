package com.example.wyklad

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    var optionname = arrayOf(
        "Gra losujaca"
    )
    var optinimage = intArrayOf(
        R.drawable.temp
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val lvProgram: ListView? = findViewById(R.id.menulist)
        val programAdapter = MenuAdapter(this, optionname, optinimage)
        lvProgram?.setAdapter(programAdapter)
    }
}