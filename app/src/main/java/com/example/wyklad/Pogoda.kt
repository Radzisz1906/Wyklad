package com.example.wyklad

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Pogoda : AppCompatActivity(), LocationListener {
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    private lateinit var tvGpsLocation: TextView
    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pogoda)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)

        }
        val f = findViewById<Button>(R.id.cokolwiek)
        f.setOnClickListener {
            getLocation()
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    override fun onLocationChanged(location: Location) {
        tvGpsLocation = findViewById(R.id.temp)
        url = "https://api.open-meteo.com/v1/forecast?" + "latitude=" + location?.latitude + "&longitude=" + location?.longitude + "&current_weather=true"
        getWeather()
    }
    fun getWeather() {
        val queue = Volley.newRequestQueue(this)
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val obj = JSONObject(response)
                val curr = JSONObject(obj["current_weather"].toString())
                tvGpsLocation.text = "Temperatura: "+curr["temperature"].toString() + " °C\n"+"Prędkość wiatru: "+curr["windspeed"].toString()+" m/s"
            },
            Response.ErrorListener { tvGpsLocation!!.text = "Nie działa" })
        queue.add(stringReq)
    }
}