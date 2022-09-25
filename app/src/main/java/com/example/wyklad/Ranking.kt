package com.example.wyklad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        supportActionBar?.hide()
        val recycleadapter = recycleadapter(mutableListOf())
        val baza = DBHelper(applicationContext);
        val messageList = findViewById<RecyclerView>(R.id.recycler)
        messageList.adapter = recycleadapter
        messageList.layoutManager = LinearLayoutManager(this)
        val button = findViewById<Button>(R.id.wroc)
        val new = baza.allUsers
        print(new.size)
        for(a in 0..10) {
            if (a>=new.size) break;

            recycleadapter.addMessage(new[a])
        }
        button.setOnClickListener{
            if (logged ==1){
                startActivity(Intent(this, Liczby::class.java));
                onStop()
            }
            if (logged ==0){
                startActivity(Intent(this, Logowanie::class.java));
                onStop()
            }
        }
    }
    }