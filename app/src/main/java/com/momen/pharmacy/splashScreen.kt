package com.momen.pharmacy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash_screen.*

class splashScreen : AppCompatActivity() {


    var myShared : SharedPreferences ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        myShared = getSharedPreferences("myShared" , 0)
        var token =  myShared?.getString("token" , "")

        val handler = Handler()
        handler.postDelayed({
            //check if the user loged in or not
            if (token != ""){
                val intent = Intent(this , FirstActivity::class.java)
                startActivity(intent)
            }else if (token == ""){
                var intent = Intent(this , loginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000)

    }
}
