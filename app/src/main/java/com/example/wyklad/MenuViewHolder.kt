package com.example.wyklad

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.wyklad.R

class MenuViewHolder internal constructor(v: View) {
    // Declare the object references for our views
    var itemImage: ImageView
    var option: TextView

    // Get the handles by calling findViewById() on View object inside the constructor
    init {
        itemImage = v.findViewById(R.id.imageView)
        option = v.findViewById(R.id.men1)
    }
}