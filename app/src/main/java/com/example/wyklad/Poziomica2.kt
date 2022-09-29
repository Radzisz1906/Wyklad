package com.example.wyklad

import android.Manifest
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Math.abs
import android.view.View

class Poziomica2 : AppCompatActivity(),SensorEventListener {
    private lateinit var mSensorManager : SensorManager
    private var mTmp : Sensor ?= null
    private var animatedview: AnimatedView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mTmp = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mSensorManager.registerListener(this, mTmp, SensorManager.SENSOR_DELAY_NORMAL)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_poziomica2)
        animatedview = AnimatedView(this)
        setContentView(animatedview)
//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            val intent = Intent(this, Menu::class.java)
//            startActivity(intent)
//
//        }
    }
    override fun onSensorChanged(event: SensorEvent) {
        val value = event.values[0];
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            animatedview!!.onSensorEvent(event)
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        print("aaaa")
    }
    inner class AnimatedView(context: Context?) : View(context) {
        private val paint: Paint
        private val paint1: Paint
        private var x = 0
        private var y = 0
        private var z = 0
        private val radius = 25
        var he = 0
        var wi = 0
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            he = h
            wi = w
        }

        fun onSensorEvent(event: SensorEvent) {
            x = x - event.values[0].toInt()
            y = y - event.values[1].toInt()
            if (x <= radius) {
                x = radius
            }
            if (x >= wi - radius) {
                x = wi - radius
            }
            if (y <= radius) {
                y = radius
            }
            if (y >= he - radius) {
                y = he - radius
            }
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawCircle(x.toFloat(), y.toFloat(), radius.toFloat(), paint)
            invalidate()
        }



        init {
            paint = Paint()
            paint.color = Color.RED
            paint1 = Paint()
            paint1.color = Color.BLUE
        }
    }
}