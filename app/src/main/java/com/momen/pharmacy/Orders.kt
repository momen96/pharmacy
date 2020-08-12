package com.momen.pharmacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.momen.pharmacy.API.ApiService
import com.momen.pharmacy.API.URL
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.order_card_view.*
import kotlinx.android.synthetic.main.order_card_view.view.*
import orderModel.orderApiInsert
import orderModel.ordersDataClass
import orderModel.userOrderclass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class Orders : AppCompatActivity(){
    // Recycler Array
    var order = ArrayList<ordersDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        mainFunction()
    }

    fun getData(){
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("orders.realm").build()
        val realm = Realm.getInstance(config)


        var allData = realm.where(orderDatabase::class.java).findAll()

        allData.forEach {
            order.add(ordersDataClass(it.Name.toString() , it.Company.toString() ,it.Note.toString(), it.Count!!.toInt()))
        }
        bindToRecycler()

    }
    fun bindToRecycler(){
        var adapter = ordersAdapter(order)
        recyclerViewOrders.layoutManager = LinearLayoutManager( this , RecyclerView.VERTICAL , false)
        recyclerViewOrders.adapter = adapter
    }
    fun mainFunction(){
        try {
            getData()
            btnSendRequest.setOnClickListener {
                insertDataToApi()
               try {
                  eraseDatabase()
               }catch (ex : Exception){
                   Toast.makeText(this , ex.message , Toast.LENGTH_LONG).show()
               }
                finish()
            }
        }catch (ex : Exception){
            Toast.makeText(this , ex.message , Toast.LENGTH_LONG).show()
        }
    }
    fun insertDataToApi(){
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("orders.realm").build()
        val realm = Realm.getInstance(config)
        var allData = realm.where(orderDatabase::class.java).findAll()

         allData.forEach {
             if (it.Note == "")
             Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService::class.java)
                .insertOrder(it.Name.toString() , it.Company.toString() , "No note" , it.Count!!.toInt())
                .enqueue(object : Callback<orderApiInsert>{
                    override fun onFailure(call: Call<orderApiInsert>, t: Throwable) {
                       // Toast.makeText(this@Orders , t.message , Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(call: Call<orderApiInsert>, response: Response<orderApiInsert>) {
                        //insert to user order table
                       var  myShared = getSharedPreferences("myShared" , 0)
                        var userId = myShared.getInt("id" , 0)
                        println("Bar $userId")
                        // here my request to the user_order database will be sent
                        Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
                            .create(ApiService::class.java)
                            .userOrderInsert(userId, response.body()!!.id)
                            .enqueue(object : Callback<userOrderclass>{
                                override fun onFailure(call: Call<userOrderclass>, t: Throwable) {

                                }

                                override fun onResponse(call: Call<userOrderclass>, response: Response<userOrderclass>) {

                                }

                            })
                    }

                })
             else
                 Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
                     .create(ApiService::class.java)
                     .insertOrder(it.Name.toString() , it.Company.toString() , it.Note.toString() , it.Count!!.toInt())
                     .enqueue(object : Callback<orderApiInsert>{
                         override fun onFailure(call: Call<orderApiInsert>, t: Throwable) {
                            // Toast.makeText(this@Orders , t.message , Toast.LENGTH_LONG).show()


                         }

                         override fun onResponse(call: Call<orderApiInsert>, response: Response<orderApiInsert>) {
                             var  myShared = getSharedPreferences("myShared" , 0)
                             var  userId = myShared.getInt("id" , 0)
                             Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()
                                 .create(ApiService::class.java)
                                 .userOrderInsert(userId, response.body()!!.id)
                                 .enqueue(object : Callback<userOrderclass>{
                                     override fun onFailure(call: Call<userOrderclass>, t: Throwable) {

                                     }

                                     override fun onResponse(call: Call<userOrderclass>, response: Response<userOrderclass>) {

                                     }

                                 })

                         }

                         })

        }



    }
    fun eraseDatabase(){
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("orders.realm").build()
        val realm = Realm.getInstance(config)
        var data = realm.where(orderDatabase::class.java).findAll()
        realm.beginTransaction()
        data.deleteAllFromRealm()
        realm.commitTransaction()
    }


}