package com.example.wyklad

import androidx.fragment.app.Fragment
import com.example.wyklad.LoadFragment.Companion.newInstance

class Animated : FragmentActivity() {
    override fun createFragment(): Fragment {
        return newInstance()
    }
}