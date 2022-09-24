package com.example.wyklad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class FragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val fm: FragmentManager = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.frag)
        if (fragment == null) {
            fragment = createFragment()
            fm.beginTransaction()
                .add(R.id.frag, fragment)
                .commit()
        }
        Thread() {
            run{
                Thread.sleep(6000)
            }
            runOnUiThread {
                val intent = Intent(this, Start::class.java)
                startActivity(intent)
                this.finish()
            }
        }.start()
    }
}