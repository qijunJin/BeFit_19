package com.example.befit

import java.io.Serializable


enum class FoodType {
    MEDITERRANEAN,
    ORIENTAL,
    AMERICAN,
    HALAL,
    MEXICAN,
    VEGETARIAN,
    OTHER
}

class Comida(val imgId: Int, val comidaName: String, val calPerServing : Int, val description : String? = null, val ingredients: MutableList<String>? = null, val type: FoodType = FoodType.OTHER) : Serializable

