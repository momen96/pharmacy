package com.momen.pharmacy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.momen.pharmacy.API.ApiService
import com.momen.pharmacy.API.RegisterData
import com.momen.pharmacy.API.URL
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class loginActivity : AppCompatActivity() {

    var myShared : SharedPreferences ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        goToSignup()

        btnLogin.setOnClickListener {

            var number = inputNumberLogin.text.toString()
            var password = inputPasswordLogin.text.toString()
            if (number.isNotEmpty() && password.isNotEmpty()){
                progressBarLogin.visibility = View.VISIBLE
                login(number , password)
            }else{
                Toast.makeText(this , "Please insert your information" , Toast.LENGTH_LONG).show()
            }

        }

    }

    fun goToSignup(){
        gotoRegister.setOnClickListener {
            var intent = Intent(this , signupActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(phone_number : String , password : String){
        var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        var api : ApiService = retrofit.create(ApiService::class.java)
        var call = api.login(phone_number , password)
        call.enqueue(object : Callback<RegisterData> {
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                progressBarLogin.visibility = View.INVISIBLE
                Toast.makeText(this@loginActivity ,"Something went wrong !!!" , Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                progressBarLogin.visibility = View.INVISIBLE
             //   Toast.makeText(this@loginActivity , response.body()!!.accessToken.toString() , Toast.LENGTH_LONG).show()
                startActivity(Intent(this@loginActivity , FirstActivity::class.java))
                finish()
                    myShared = getSharedPreferences("myShared" , 0)
                    var editor : SharedPreferences.Editor = myShared!!.edit()
                    editor.putString("token" , response.body()!!.accessToken)
                    editor.putString("name" , response.body()!!.name)
                    editor.putString("phone" , response.body()!!.phone_number)
                    editor.putString("address" , response.body()!!.address)
                    editor.putInt("id" , response.body()!!.id)
                    editor.commit()

                inputNumberLogin.text = null
                inputPasswordLogin.text = null
            }

        })

    }
}



