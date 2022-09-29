package com.example.wyklad

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    var optionname = arrayOf(
        "Gra losujaca",
        "Temperatura",
        "Poziom światła",
        "Poziomica",
        "Wilgotność",
        "Ciśnienie",
        "Kompas",
        "Koordynaty"
    )
    var optinimage = intArrayOf(
        R.drawable.numb,
        R.drawable.temp,
        R.drawable.light,
        R.drawable.level,
        R.drawable.hum,
        R.drawable.press,
        R.drawable.compass,
        R.drawable.compass
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val lvProgram: ListView? = findViewById(R.id.menulist)
        val programAdapter = MenuAdapter(this, optionname, optinimage)
        lvProgram?.setAdapter(programAdapter)
    }
}