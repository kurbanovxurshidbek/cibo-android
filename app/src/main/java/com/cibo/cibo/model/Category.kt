package com.cibo.cibo.model

class Category(val name: String, vararg food: Food) {
    val listOfFoods: List<Food> = food.toList()
}