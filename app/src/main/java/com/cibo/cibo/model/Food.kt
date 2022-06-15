package com.cibo.cibo.model

import java.io.Serializable

data class Food(
    val attachId: String,
    val categoryId: String,
    val description: String,
    val id: String,
    val name: String,
    val price: Int
):Serializable