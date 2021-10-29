package com.example.mobile

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mobile.sec

class MainActivity : AppCompatActivity() {


    lateinit var receiver:AirplaneModeChanged


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        receiver=AirplaneModeChanged()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver,it)
        }


        println("MainActivity onCreate")

        val sharedpref = getSharedPreferences("mypref", MODE_PRIVATE)
        val editor=sharedpref.edit()

        // get reference to all views
        var tv_crt = findViewById<TextView>(R.id.tv_crt)
        var User_Name = findViewById<EditText>(R.id.etUsername)
        var Password = findViewById<EditText>(R.id.etPassword)
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        User_Name.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(User_Name.text.toString()).matches())
                    btn_submit.isEnabled=true
                else{
                    btn_submit.isEnabled=false
                    User_Name.setError("Invalid Email")
                }
            }

        })
        tv_crt.setOnClickListener{

        }

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click

            User_Name.setText("")
            Password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val etUsername = User_Name.text.toString().trim()
            val etPassword = Password.text.toString().trim()
            if (etUsername.isEmpty()){
                User_Name.error="Username Required"
                return@setOnClickListener
            }else if (etPassword.isEmpty()){
                Password.error="Password Required"
                return@setOnClickListener
            }else{
                val intent = Intent(this@MainActivity, sec::class.java)
                intent.putExtra("User_Name",etUsername)
                startActivity(intent)
                Toast.makeText(this@MainActivity, etUsername, Toast.LENGTH_LONG).show()

            }


            // your code to validate the user_name and password combination
            // and verify the same

        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }


}