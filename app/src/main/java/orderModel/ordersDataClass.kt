package orderModel

data class ordersDataClass(var Name : String ,
                           var Company : String ,
                           var Notes : String ,
                           var Count : Int
)


data class orderApiInsert(var id : Int ,
                          var order_name : String ,
                          var order_company : String ,
                          var order_note : String ,
                          var order_count : Int
)

data class userOrderclass(var id : Int ,
                          var user_id : Int ,
                          var order_id : Int
)