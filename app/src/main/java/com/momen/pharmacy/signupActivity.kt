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
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class signupActivity : AppCompatActivity() {

    var myShared : SharedPreferences ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btnSignUp.setOnClickListener {

            var name = signupName.text.toString()
            var phone_number = signupPhone.text.toString()
            var address = signupAddress.text.toString()
            var password = signupPassword.text.toString()

            if(name.isNotEmpty() && phone_number.isNotEmpty() && address.isNotEmpty() && password.isNotEmpty()){
                progressBarSignup.visibility = View.VISIBLE
                signUp(name , phone_number , address , password)
            }else{
                Toast.makeText(this , "Please insert you information" ,Toast.LENGTH_LONG).show()
            }

        }

    }

    fun signUp(name : String , phone_number : String , address : String , password : String){
        var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        var api : ApiService = retrofit.create(ApiService::class.java)
        var call = api.register(name , phone_number , address , password)
        call.enqueue(object : Callback<RegisterData>{
            override fun onFailure(call: Call<RegisterData>, t: Throwable) {
                progressBarSignup.visibility = View.INVISIBLE
              Toast.makeText(this@signupActivity ,"No internet connection" , Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                progressBarSignup.visibility = View.INVISIBLE
             //   Toast.makeText(this@signupActivity , response.body()!!.accessToken , Toast.LENGTH_LONG).show()
                eraseEditText()
                startActivity(Intent(this@signupActivity , FirstActivity::class.java))
                myShared = getSharedPreferences("myShared" , 0)
                var editor : SharedPreferences.Editor = myShared!!.edit()
                editor.putString("token" , response.body()!!.accessToken)
                editor.putString("name" , response.body()!!.name)
                editor.putString("phone" , response.body()!!.phone_number)
                editor.putString("address" , response.body()!!.address)
                editor.putInt("id" , response.body()!!.id)
                editor.commit()

            }

        })

    }

    fun eraseEditText(){
        signupName.text = null
        signupPhone.text = null
        signupAddress.text = null
        signupPassword.text = null
    }


}

