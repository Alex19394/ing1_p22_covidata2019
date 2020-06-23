package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val graph = findViewById(R.id.graphic_but) as Button

        graph.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, GraphicActivity::class.java);
                startActivity(intent);
            }
        })

        val dat = findViewById(R.id.data_but) as Button

        dat.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, DataActivity::class.java);
                startActivity(intent);
            }
        })

        val myst = findViewById(R.id.mystery_but) as Button

        myst.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(applicationContext, MysteryActivity::class.java);
                startActivity(intent);
            }
        })


    }

}