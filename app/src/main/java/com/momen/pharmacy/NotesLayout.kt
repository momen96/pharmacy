package com.momen.pharmacy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.SyncUser.all
import kotlinx.android.synthetic.main.activity_notes_layout.*
import kotlinx.android.synthetic.main.card_view.view.*

class NotesLayout : AppCompatActivity() {

    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_layout)

            getNameCompanyFromDatabase()
            btnPressed()

        }


    fun getNameCompanyFromDatabase(){

        val config = RealmConfiguration.Builder().name("item.realm").build()
        val realm = Realm.getInstance(config)


        var allData = realm.where(RealmDatabase::class.java).findAll()


        var itemName = allData[realm.where(RealmDatabase::class.java).findAll().size -1]!!.name
        var itemCompany = allData[realm.where(RealmDatabase::class.java).findAll().size -1]!!.company
        txtItemName.text = itemName
        txtCompanyName.text = itemCompany
    }
    fun btnPressed(){
        btnAddCount.setOnClickListener {
            count++
            txtItemCount.text = count.toString()
        }
        btnMinus.setOnClickListener {
            if (count !=1){
                count--
                txtItemCount.text = count.toString()
            }
        }
        btnConfirm.setOnClickListener {
            insertDataToDatabase()
           // var intent = Intent(this , MainActivity::class.java)
           // startActivity(intent)
            finish()
        }
    }
    fun insertDataToDatabase(){
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("orders.realm").build()
        val realm = Realm.getInstance(config)
        realm.beginTransaction()

        var id = realm.where(orderDatabase::class.java).findAll().size

        val order = realm.createObject(orderDatabase::class.java ,id+1)
        order.Name = txtItemName.text.toString()
        order.Company = txtCompanyName.text.toString()
        order.Note = txtNote.text.toString()
        order.Count = txtItemCount.text.toString()
        realm.commitTransaction()
    }

}




// name("orders.realm").build()