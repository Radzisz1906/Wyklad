package com.example.wyklad

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numb = findViewById<EditText>(R.id.liczba)
        numb.transformationMethod = null;
        val button = findViewById<Button>(R.id.Button)
        val rank = findViewById<Button>(R.id.ranking)
        val shots = findViewById<TextView>(R.id.shots)
        val poi = findViewById<TextView>(R.id.punkty)
        val baza = DBHelper(applicationContext);
//        val toast1 = Toast.makeText(applicationContext,"Cos",Toast.LENGTH_SHORT)
        var points = 0;
        val builder = AlertDialog.Builder(this@MainActivity);
        var liczba = Random.nextInt(0,20);
        var strzaly = 0;
        builder.setTitle("Przegrałeś");
        builder.setMessage(":(");
        val wyg = AlertDialog.Builder(this@MainActivity);
        builder.setTitle("Wygrałeś!");

        button.setOnClickListener {
            if (numb.text.toString().toInt()>20){
                Toast.makeText(this, "Liczba poza zakresem", Toast.LENGTH_SHORT).show();
            }
            else if (numb.text.toString().toInt()>liczba){
                Toast.makeText(this, "Za duzo", Toast.LENGTH_SHORT).show();
                strzaly+=1;
                shots.text = "Strzały: ".plus(strzaly.toString());

            }
            else if (numb.text.toString().toInt()<liczba){
                Toast.makeText(this, "Za malo", Toast.LENGTH_SHORT).show();
                strzaly+=1;
                shots.text = "Strzały: ".plus(strzaly.toString());
            }
            else if (numb.text.toString().toInt()==liczba){
                Toast.makeText(this, "Trafione", Toast.LENGTH_SHORT).show();
                when(strzaly){
                    1 -> points = 5;
                    3 -> points = 3;
                    2 -> points = 3;
                    4 -> points = 3;
                    5 -> points = 2;
                    6 -> points = 2;
                    7 -> points = 1;
                    8 -> points = 1;
                    9 -> points = 1;
                    10 -> points = 1;
                    else -> {
                        points = 0;
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                }
                if (points>0){
                    if (baza.SetRecord(us,points)){
                        val sharedPreference =  getSharedPreferences("Rekordy", Context.MODE_PRIVATE)
                        var editor = sharedPreference.edit()
                        editor.putInt("Rekord",points)
                        editor.commit()
                    }
                    builder.setMessage(points.toString().plus(" punkty"));
                    val dialog: AlertDialog = builder.create()
                    dialog.show()



                }
                liczba = Random.nextInt(0,20);
                strzaly=0;
                points=0
                shots.text = "Strzały: ".plus(strzaly.toString());
                poi.text = "Punkty: ".plus(points.toString());

            }
        }
        rank.setOnClickListener{
            startActivity(Intent(this, Ranking::class.java));
            onStop();
        }
    }
}