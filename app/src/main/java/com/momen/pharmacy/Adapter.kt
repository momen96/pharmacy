package com.momen.pharmacy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.momen.pharmacy.API.itemModel
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.card_view.view.*


class Adapter (var item : ArrayList<itemModel>) : RecyclerView.Adapter<Adapter.myViewHolder>(){

    var filterListResult : List<itemModel>
    init {
        this.filterListResult = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.card_view , parent , false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {

        return filterListResult.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        var card : itemModel = item[position]
        holder.txtName.text = card.item_name
        holder.txtCompany.text = card.item_company

        holder.bind(position)

    }


    class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var txtName = itemView.findViewById(R.id.txtItemName) as TextView
        var txtCompany = itemView.findViewById(R.id.txtCompanyName) as TextView

        //On click the recycler view
        fun bind( position: Int){


           // var id = realm.where(RealmDatabase::class.java).findAll().size + 1

            itemView.setOnClickListener {


            var intent = Intent(itemView.context , NotesLayout::class.java)
                itemView.context.startActivity(intent)

                Realm.init(itemView.context)
                val config = RealmConfiguration.Builder().name("item.realm").build()
                val realm = Realm.getInstance(config)
                realm.beginTransaction()

                var id = realm.where(RealmDatabase::class.java).findAll().size

                val person = realm.createObject(RealmDatabase::class.java ,id+1)
                person.name = itemView.txtItemName.text.toString()
                person.company = itemView.txtCompanyName.text.toString()
                realm.commitTransaction()
            }


        }



    }

}