package com.example.befit

import java.io.Serializable

class Exercise(val imgId: Int = 0, val exerciseName: String = "", val calories: Int = 0) :
    Serializable {
    fun isNull(): Boolean {
        return (exerciseName.isNullOrEmpty())
    }
}
