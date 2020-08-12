package com.momen.pharmacy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.momen.pharmacy.API.ApiService
import com.momen.pharmacy.API.RegisterData
import com.momen.pharmacy.API.URL
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstActivity : AppCompatActivity() {
    var myShared : SharedPreferences ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        myShared = getSharedPreferences("myShared" , 0)
        var token = myShared?.getString("token" , " ")
        itemClicked()
        logOut.setOnClickListener {
            myShared!!.edit().clear().commit()
            var intent = Intent(this@FirstActivity , loginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    fun itemClicked(){
        wareHouse.setOnClickListener {
            var intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
        cart.setOnClickListener {
            var intent = Intent(this , Orders::class.java)
            startActivity(intent)
        }

        myAccount.setOnClickListener {
            var intent = Intent(this , com.momen.pharmacy.myAccount::class.java)
            startActivity(intent)
        }



    }
/*
    fun logout(token : String){
        var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        var api : ApiService = retrofit.create(ApiService::class.java)
        var call = api.logout(token)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@FirstActivity ,"Something went wrong !!!" , Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                myShared!!.edit().clear().commit()
                var intent = Intent(this@FirstActivity , loginActivity::class.java)
                startActivity(intent)
                finish()

            }

        })

    }
*/
}
