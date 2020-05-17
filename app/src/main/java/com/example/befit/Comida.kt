package com.example.befit

import java.io.Serializable

class Comida(val imgId: Int, val comidaName: String, val calPerServing : Int, val description : String? = null, val ingredients: MutableList<String>? = null) : Serializable