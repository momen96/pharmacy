package com.momen.pharmacy.API

import com.momen.pharmacy.changePassword
import com.momen.pharmacy.orderDatabase
import okhttp3.ResponseBody
import orderModel.orderApiInsert
import orderModel.ordersDataClass
import orderModel.userOrderclass
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("register")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun register(
        @Field("name") name:String,
        @Field("phone_number") phone_number:String,
        @Field("address") address:String,
        @Field("password") password:String
    ) : Call<RegisterData>

    @POST("login")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun login(
        @Field("phone_number") phone_number:String,
        @Field("password") password:String
    ) : Call<RegisterData>

    @POST("logout")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun logout(
        @Field("api_token") api_token:String
    ) : Call<ResponseBody>

    @get:GET("items")
    var itemsApi : Call<ArrayList<itemModel>>


    @POST("orderinsert")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun insertOrder(
        @Field("order_name") order_name : String,
        @Field("order_company") order_company : String,
        @Field("order_note") order_note : String,
        @Field("order_count") order_count : Int
    ) : Call<orderApiInsert>

    @POST("insertrelation")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun userOrderInsert(
        @Field("user_id") user_id : Int,
        @Field("order_id") order_id : Int
    ) : Call<userOrderclass>
/*
    @PUT("updateuser/{id}")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun updateData(
        @Path("id") id : Int,
        @Field("name") name:String,
        @Field("phone_number") phone_number:String,
        @Field("address") address:String
    ) : Call<RegisterData>

 */

    @POST("edituser/{id}")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun updateData(
        @Path("id") id : Int,
        @Field("name") name:String,
        @Field("phone_number") phone_number:String,
        @Field("address") address:String
    ) : Call<RegisterData>

    @POST("changepassword/{id}")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    fun changePassword(
        @Path("id") id : Int,
        @Field("oldPassword") oldPassword:String,
        @Field("password") password:String
    ) : Call<RegisterData>



}