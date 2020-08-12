package com.momen.pharmacy

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class RealmDatabase : RealmObject(){

    @PrimaryKey
    var id : Int = 0
    var name : String ? = null
    var company : String ? = null

}