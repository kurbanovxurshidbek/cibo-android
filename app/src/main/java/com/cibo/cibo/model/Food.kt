package com.cibo.cibo.model

import java.io.Serializable

data class Food(val content: String, var img: String? = null, var about: String? = null) :
    Serializable
