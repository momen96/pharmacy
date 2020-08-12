package com.momen.pharmacy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.momen.pharmacy.API.ApiService
import com.momen.pharmacy.API.RegisterData
import com.momen.pharmacy.API.URL
import com.momen.pharmacy.API.itemModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var recyclerViewcontent = ArrayList<itemModel>()
    var recyclerViewContentSearch = ArrayList<itemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RecyclerViewShowData()

    }

    fun RecyclerViewShowData(){

        recyclerViewItems.layoutManager = LinearLayoutManager( this , RecyclerView.VERTICAL , false)

        var retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java).itemsApi.enqueue(object : Callback<ArrayList<itemModel>>{
            override fun onResponse(call: Call<ArrayList<itemModel>>, response: Response<ArrayList<itemModel>>){
                recyclerViewcontent = response.body()!!
                //new array list from old one
                recyclerViewContentSearch.addAll(recyclerViewcontent)
                recyclerViewItems.adapter = Adapter(recyclerViewContentSearch)
            }

            override fun onFailure(call: Call<ArrayList<itemModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity , "Something went wrong" , Toast.LENGTH_LONG).show()
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu , menu)
        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem != null){
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()){
                        recyclerViewContentSearch.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        recyclerViewcontent.forEach {
                            if (it.item_name.toLowerCase(Locale.getDefault()).contains(search)){
                                recyclerViewContentSearch.add(it)
                            }
                        }

                        recyclerViewItems.adapter!!.notifyDataSetChanged()

                    }else{
                        recyclerViewContentSearch.clear()
                        recyclerViewContentSearch.addAll(recyclerViewcontent)
                        recyclerViewItems.adapter!!.notifyDataSetChanged()
                    }


                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
