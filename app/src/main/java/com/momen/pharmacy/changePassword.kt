package com.momen.pharmacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.momen.pharmacy.API.ApiService
import com.momen.pharmacy.API.RegisterData
import com.momen.pharmacy.API.URL
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_my_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class changePassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        var myShared = getSharedPreferences("myShared" , 0)

        btnChangePassword.setOnClickListener {

            var oldPassword = editTextOld.text.toString()
            var newPassword = editTextNew.text.toString()
            var confirmPassword = editTextConfirm.text.toString()

            if (newPassword == confirmPassword){
                var id =  myShared.getInt("id" , 0)
                var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(
                    GsonConverterFactory.create())
                    .build()
                var api : ApiService = retrofit.create(ApiService::class.java)
                var call = api.changePassword(id , oldPassword , newPassword)
                call.enqueue(object : Callback<RegisterData>{
                    override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {
                        Toast.makeText(this@changePassword , "Password has been changed" , Toast.LENGTH_LONG).show()
                        finish()

                    }

                })
            }else{
                Toast.makeText(this , "Passwords is not matching" , Toast.LENGTH_LONG).show()
            }


        }


    }
}
