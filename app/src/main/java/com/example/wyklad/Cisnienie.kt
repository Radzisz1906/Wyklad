package com.example.wyklad

import android.Manifest
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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

class Cisnienie : AppCompatActivity(),SensorEventListener {
    private lateinit var mSensorManager : SensorManager
    private var mTmp : Sensor ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mTmp = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        mSensorManager.registerListener(this, mTmp, SensorManager.SENSOR_DELAY_NORMAL)
        val tmp = findViewById<TextView>(R.id.temp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cisnienie)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)

        }}
    override fun onSensorChanged(event: SensorEvent) {
        val tmp = findViewById<TextView>(R.id.temp)
        val value = event.values[0];
        if (event?.sensor?.type == Sensor.TYPE_PRESSURE) {
            tmp.text = value.toString().plus(" hPa");
            SharedData.temperature = value
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        print("aaaa")
    }

}