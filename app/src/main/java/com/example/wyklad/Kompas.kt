package com.example.wyklad

import android.content.Intent
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Kompas : AppCompatActivity(), SensorEventListener {
    private var imageView: ImageView? = null
    private val grav = FloatArray(3)
    private val geom = FloatArray(3)
    private var azim = 0f
    private var currentaz = 0f
    private var mSenMen: SensorManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kompas)
        imageView = findViewById<View>(R.id.compass) as ImageView
        mSenMen = getSystemService(SENSOR_SERVICE) as SensorManager
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        mSenMen!!.registerListener(
            this,
            mSenMen!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
            SensorManager.SENSOR_DELAY_GAME
        )
        mSenMen!!.registerListener(
            this,
            mSenMen!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        mSenMen!!.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val alfa = 0.97f
        synchronized(this) {
            if (sensorEvent.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                grav[0] = alfa * grav[0] + (1 - alfa) * sensorEvent.values[0]
                grav[1] = alfa * grav[1] + (1 - alfa) * sensorEvent.values[1]
                grav[2] = alfa * grav[2] + (1 - alfa) * sensorEvent.values[2]
            }
            if (sensorEvent.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                geom[0] = alfa * geom[0] + (1 - alfa) * sensorEvent.values[0]
                geom[1] = alfa * geom[1] + (1 - alfa) * sensorEvent.values[1]
                geom[2] = alfa * geom[2] + (1 - alfa) * sensorEvent.values[2]
            }
            val X = FloatArray(9)
            val Y = FloatArray(9)
            val sukces = SensorManager.getRotationMatrix(X, Y, grav, geom)
            if (sukces) {
                val polozenie = FloatArray(3)
                SensorManager.getOrientation(X, polozenie)
                azim = Math.toDegrees(polozenie[0].toDouble()).toFloat()
                azim = (azim + 360) % 360
                val an: Animation = RotateAnimation(
                    -currentaz,
                    -azim,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                currentaz = azim
                an.duration = 500
                an.repeatCount = 0
                an.fillAfter = true
                imageView!!.startAnimation(an)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
}