package com.cibo.cibo.model

data class ItemHistory(
    val order_number : String,
    val date : String,
    val order_name : String,
    val price : String,
    val pay_type: String,
    val location : String
)