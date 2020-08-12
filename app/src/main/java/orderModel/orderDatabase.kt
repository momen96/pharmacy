package com.momen.pharmacy

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class orderDatabase : RealmObject(){

    @PrimaryKey
    var Id : Int = 0
    var Name : String ? = null
    var Company : String ? = null
    var Note : String ? = null
    var Count : String ? = null
}