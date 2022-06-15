package com.cibo.cibo.model

data class Category(
    val createdDate: String,
    val foodList: List<Food>,
    val id: String,
    val name: String
)