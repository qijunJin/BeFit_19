package com.example.befit

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoToEat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_eat)

        var comida = intent.getSerializableExtra("comida") as Comida

        var imgcomidaId: ImageView = findViewById(R.id.imgComidaId)
        var comidaName: TextView = findViewById(R.id.comidaName)
        var ingList: TextView = findViewById(R.id.ing_list)
        var dishcal: TextView = findViewById(R.id.dishcal)
        imgcomidaId.setImageResource(comida.imgId)
        comidaName.text = comida.comidaName
        ingList.text = toListString(comida.ingredients!!)
        comidaName.typeface = Typeface.createFromAsset(assets, "font/futura-pt-heavy.otf")
        ingList.typeface = Typeface.createFromAsset(assets, "font/futura-pt-book-oblique.otf")
        dishcal.text = comida.calPerServing.toString() + " kcal / serving"
        dishcal.typeface = Typeface.createFromAsset(assets, "font/futura-pt-demibold.otf")
    }

    private fun toListString(string_list: MutableList<String>): String {
        var result: String
        result = ""
        for (i in 0..string_list.size - 2) {
            result += string_list.get(i) + "\n"
        }
        result += string_list.get(string_list.size - 1)
        return result
    }
}
