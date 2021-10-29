package com.example.mobile
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.example.mobile.MainActivity
import com.example.mobile.R

class sec : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        var btnBack = findViewById(R.id.btnBack) as Button
        val actionBar = supportActionBar

        var intentValue = ""

        val edTv = findViewById<TextView>(R.id.edTv)
        intentValue = intent.getStringArrayExtra("User_Name").toString()
        println("intentValues " + intent.getStringExtra("User_Name"))
        edTv.setText(intentValue)

        btnBack.setOnClickListener {
            startService(Intent(applicationContext, MyService::class.java)) //Call the MyService.kt file on (startService) method

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }}