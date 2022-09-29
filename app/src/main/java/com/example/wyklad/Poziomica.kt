package com.example.wyklad

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class Poziomica : AppCompatActivity(), SensorEventListener {
    private var accelerometer: Sensor? = null
    private lateinit var accelerometerManager: SensorManager
    private var animatedview: AnimatedView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        accelerometerManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = accelerometerManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        accelerometerManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        animatedview = AnimatedView(this)
        setContentView(animatedview)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            animatedview!!.onSensorEvent(event)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onPause() {
        super.onPause()
        accelerometerManager.unregisterListener(this)
    }

    override fun onPostResume() {
        super.onPostResume()
        accelerometerManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        accelerometerManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
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