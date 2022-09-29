package com.example.wyklad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

public var logged = 0;
public var us = ""
class Logowanie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val mydatabase = openOrCreateDatabase("Dane", MODE_PRIVATE, null)
//        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Dane(login VARCHAR,haslo VARCHAR, punkty number);");


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logowanie)
        val button_logowanie = findViewById<Button>(R.id.zaloguj);
        val button_rejestracja = findViewById<Button>(R.id.zarejestruj)
        val button_ranking = findViewById<Button>(R.id.rank)
        var log = findViewById<EditText>(R.id.Login)
        var pas = findViewById<EditText>(R.id.password)
        val baza = DBHelper(applicationContext);
//        var logstr:String = log.text.toString();
//        var passtr:String = pas.text.toString();
        button_rejestracja.setOnClickListener{
//            Toast.makeText(this, log.text.toString(), Toast.LENGTH_SHORT).show();

            if (log.text.toString().isNotEmpty() && pas.text.toString().isNotEmpty() && baza.AddUser(log.text.toString(),pas.text.toString(),0)){
                logged = 1;
                us = log.text.toString()
                startActivity(Intent(this, Menu::class.java));
                onStop();
            }
            else{
                Toast.makeText(this, "Użytkownik o podanym loginie już istnieje", Toast.LENGTH_SHORT).show();
            }

        }
        button_logowanie.setOnClickListener{
            if (baza.LoginUser(log.text.toString(),pas.text.toString())){
                logged = 1;
                us = log.text.toString()
                startActivity(Intent(this, Liczby::class.java));
                onStop();
            }
            else{
                Toast.makeText(this, "Błędny login lub hasło", Toast.LENGTH_SHORT).show();
            }
        }
        button_ranking.setOnClickListener{
            startActivity(Intent(this, Ranking::class.java));
            onStop();
        }
        val button = findViewById<Button>(R.id.but)
        button.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)

        }

    }
}