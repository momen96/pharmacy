package com.momen.pharmacy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.order_card_view.view.*
import orderModel.ordersDataClass

class ordersAdapter(var order : ArrayList<ordersDataClass>) : RecyclerView.Adapter<ordersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ordersAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_card_view , parent , false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return order.size
    }

    override fun onBindViewHolder(holder: ordersAdapter.ViewHolder, position: Int) {


        val data : ordersDataClass = order[position]
        holder.txtOrderName.text = data.Name
        holder.txtOrderCompany.text = data.Company
        holder.txtOrderCount.text = data.Count.toString()
        // recycler View item name
        var recItem : String = holder.txtOrderName.text.toString()

        holder.imageDelete.setOnClickListener {
           deleteItemFromRecyclerView(position , recItem)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var txtOrderName = itemView.txtOrderName as TextView
        var txtOrderCompany = itemView.txtOrderCompany as TextView
        var txtOrderCount = itemView.txtOrderCount as TextView
        var imageDelete = itemView.imageDelete as ImageView

    }
    fun deleteItemFromRecyclerView(position: Int , recItem : String){
        val config = RealmConfiguration.Builder().name("orders.realm").build()
        val realm = Realm.getInstance(config)
        var data = realm.where(orderDatabase::class.java).equalTo("Name" ,recItem)
            .findFirst()
        realm.beginTransaction()
        data!!.deleteFromRealm()
        realm.commitTransaction()
        order.removeAt(position)
        notifyItemRangeChanged(position  , order.size)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}