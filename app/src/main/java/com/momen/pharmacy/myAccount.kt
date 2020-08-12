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
import kotlinx.android.synthetic.main.activity_my_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class myAccount : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)
        visibility()
        changePassword()

        // Insert the data from the shared preferences
        var myShared = getSharedPreferences("myShared" , 0)
        var name : String? = myShared.getString("name" , "NULL")
        var phone : String? = myShared.getString("phone" , "NULL")
        var address : String? = myShared.getString("address" , "NULL")
        txtName.text = name
        txtPhone.text = phone
        txtAddress.text = address
        // End here !!!


        // Update Information
        var nameinfo : String ?  = txtName.text.toString()
        var phoneInfo : String ? = txtPhone.text.toString()
        var addressInfo : String ? = txtAddress.text.toString()

       btnSave.setOnClickListener {
           if (NameMyAcconut.text.isEmpty() && AddressMyAccount.text.isEmpty() && PhoneMyAccount.text.isEmpty()){
               Toast.makeText(this , "Nothing to do !!" , Toast.LENGTH_LONG).show()
           }

           else if (NameMyAcconut.text.isNotEmpty() && AddressMyAccount.text.isEmpty() && PhoneMyAccount.text.isEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , NameMyAcconut.text.toString() , phoneInfo!! , addressInfo!!)
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
//                       editor.putString("name" , response.body()!!.name)
                       editor.putString("name" , NameMyAcconut.text.toString())
                       editor.commit()
                   }

               })
               finish()


           }

           else if (NameMyAcconut.text.isEmpty() && AddressMyAccount.text.isNotEmpty() && PhoneMyAccount.text.isEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , nameinfo!! , phoneInfo!! , AddressMyAccount.text.toString())
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
                     //  editor.putString("address" , response.body()!!.address)
                       editor.putString("address" , AddressMyAccount.text.toString())
                       editor.commit()


                   }

               })
               finish()

           }

           else if (NameMyAcconut.text.isEmpty() && AddressMyAccount.text.isEmpty() && PhoneMyAccount.text.isNotEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , nameinfo!! , PhoneMyAccount.text.toString() , addressInfo!!)
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
                     //  editor.putString("phone" , response.body()!!.phone_number)
                       editor.putString("phone" , PhoneMyAccount.text.toString())
                       editor.commit()


                   }

               })
               finish()

           }

           else if (NameMyAcconut.text.isNotEmpty() && AddressMyAccount.text.isNotEmpty() && PhoneMyAccount.text.isEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , NameMyAcconut.text.toString() , phoneInfo!! , AddressMyAccount.text.toString())
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
//                       editor.putString("name" , response.body()!!.name)
//                       editor.putString("address" , response.body()!!.address)
                       editor.putString("name" , NameMyAcconut.text.toString())
                       editor.putString("address" , AddressMyAccount.text.toString())
                       editor.commit()


                   }

               })
               finish()

           }

           else if (NameMyAcconut.text.isNotEmpty() && AddressMyAccount.text.isEmpty() && PhoneMyAccount.text.isNotEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , NameMyAcconut.text.toString() , PhoneMyAccount.text.toString() , addressInfo!!)
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
//                       editor.putString("name" , response.body()!!.name)
//                       editor.putString("phone" , response.body()!!.phone_number)
                       editor.putString("name" , NameMyAcconut.text.toString())
                       editor.putString("phone" , PhoneMyAccount.text.toString())
                       editor.commit()

                   }

               })
               finish()

           }

           else if (NameMyAcconut.text.isEmpty() && AddressMyAccount.text.isNotEmpty() && PhoneMyAccount.text.isNotEmpty()){

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , nameinfo!! , PhoneMyAccount.text.toString() , AddressMyAccount.text.toString())
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
//                       editor.putString("address" , response.body()!!.address)
//                       editor.putString("phone" , response.body()!!.phone_number)
                       editor.putString("address" , AddressMyAccount.text.toString())
                       editor.putString("phone" , PhoneMyAccount.text.toString())
                       editor.commit()

                   }

               })
               finish()

           }

           else {

               var id =  myShared.getInt("id" , 0)
               var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                   .build()
               var api : ApiService = retrofit.create(ApiService::class.java)
               var call = api.updateData(id , NameMyAcconut.text.toString() , PhoneMyAccount.text.toString() , AddressMyAccount.text.toString())
               call.enqueue(object : Callback<RegisterData>{
                   override fun onFailure(call: Call<RegisterData>, t: Throwable) {

                   }

                   override fun onResponse(call: Call<RegisterData>, response: Response<RegisterData>) {

                       var editor : SharedPreferences.Editor = myShared!!.edit()
//                       editor.putString("name" , response.body()!!.name)
//                       editor.putString("address" , response.body()!!.address)
//                       editor.putString("phone" , response.body()!!.phone_number)
                       editor.putString("name" , NameMyAcconut.text.toString())
                       editor.putString("address" , AddressMyAccount.text.toString())
                       editor.putString("phone" , PhoneMyAccount.text.toString())

                       editor.commit()

                   }

               })
               finish()

           }



       }


    }

    fun visibility(){
        changeName.setOnClickListener {
            txtName.visibility = View.INVISIBLE
            NameMyAcconut.visibility = View.VISIBLE
        }
        changeAddress.setOnClickListener {
            txtAddress.visibility = View.INVISIBLE
            AddressMyAccount.visibility = View.VISIBLE
        }
        changePhone.setOnClickListener {
            txtPhone.visibility = View.INVISIBLE
            PhoneMyAccount.visibility = View.VISIBLE
        }
    }
    fun changePassword(){
            changePassword.setOnClickListener {
                var intent = Intent(this , com.momen.pharmacy.changePassword::class.java)
                startActivity(intent)
            }
    }

}
