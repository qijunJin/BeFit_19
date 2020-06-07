package com.example.befit
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [fragment_comida.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_comida : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var t: GenericTypeIndicator<List<String>>
    var foodList: ArrayList<Comida> = ArrayList()

    var FoodPreferences : ArrayList<FoodType> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        MainActivity.reference.child(MainActivity.user_actual.complete_name)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}
                override fun onDataChange(p0: DataSnapshot) {  //Mirem si el nom d'usuari ja existeix a firebase. En cas que existeixi no ha de registrar i passem a Main_Interface directament
                    if (p0.child("age").exists()) {
                        MainActivity.user_actual.name = p0.child("name").value.toString()
                        MainActivity.user_actual.age = p0.child("age").value.toString().toInt()
                        MainActivity.user_actual.height =
                            p0.child("height").value.toString().toDouble()
                        MainActivity.user_actual.weight =
                            p0.child("weight").value.toString().toDouble()
                        MainActivity.user_actual.cal = p0.child("cal").value.toString().toInt()

                        p0.child("arr_comida").children.forEach {
                            MainActivity.user_actual.arr_comida.add(it.value.toString())
                            //Toast.makeText(requireContext(),it.value.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        for(i in MainActivity.user_actual.arr_comida){
            when(i){
                "MEDITERRANEAN" -> FoodPreferences.add(FoodType.MEDITERRANEAN)
                "ORIENTAL" -> FoodPreferences.add(FoodType.ORIENTAL)
                "AMERICAN" -> FoodPreferences.add(FoodType.AMERICAN)
                "HALAL" -> FoodPreferences.add(FoodType.HALAL)
                "MEXICAN" -> FoodPreferences.add(FoodType.MEXICAN)
                "VEGETARIAN" -> FoodPreferences.add(FoodType.VEGETARIAN)
            }
        }
        //Food initzialization
        foodList.add(Comida(R.drawable.seafood_paella, "Seafood Paella", 379,
            ingredients = mutableListOf("Rice","Fish Stock","Musseles", "Shrimp"), description = "This is the description",
            type= FoodType.MEDITERRANEAN))
        foodList.add(Comida(R.drawable.baked_italian_chicken, "Baked Chicken", 340,
            ingredients = mutableListOf("Chicken","Potatoes","Cherry Tomatoes", "Olive oil"), description = "This is the description",
            type= FoodType.MEDITERRANEAN))
        foodList.add(Comida(R.drawable.spring_rolls, "Spring Rolls", 154,
            ingredients = mutableListOf("Grated Vegetables","Flour","Salt and pepper", "Soy Sauce"), description = "This is the description",
            type= FoodType.ORIENTAL))
        foodList.add(Comida(R.drawable.ramen_adobe_m, "Ramen", 200,
            ingredients = mutableListOf("Sliced Cooked Pork","Garlic Coves","Ramen noodles", "Sesame Oil"), description = "This is the description",
            type= FoodType.ORIENTAL))
        foodList.add(Comida(R.drawable.cobb_salad, "Cobb Salad", 400,
            ingredients = mutableListOf("Avocado","Tomato", "Hard Boiled Egg","Dressing"), description = "This is the description",
            type= FoodType.AMERICAN))
        foodList.add(Comida(R.drawable.fried_chicken, "Fried Chicken", 190,
            ingredients = mutableListOf("Chicken","Flour","Oil"), description = "This is the description",
            type= FoodType.AMERICAN))
        foodList.add(Comida(R.drawable.kibbeh, "Kibbeh", 365,
            ingredients = mutableListOf("Fine Bulgur Wheat","Cinnamon","Beef", "Salt and Pepper"), description = "This is the description",
            type= FoodType.HALAL))
        foodList.add(Comida(R.drawable.nachos, "Nachos", 346,
            ingredients = mutableListOf("Refried Beans","Cheese","Guacamole", "Jalapeño"), description = "This is the description",
            type= FoodType.MEXICAN))
        foodList.add(Comida(R.drawable.tamales, "Chicken Tamales", 141,
            ingredients = mutableListOf("Pork Loin","Flour Dough","Dried Corn Husks", "Sour Cream"), description = "This is the description",
            type= FoodType.MEXICAN))
        foodList.add(Comida(R.drawable.pasta_salad, "Pasta Salad", 356,
            ingredients = mutableListOf("Cooked Pasta","Cherry Tomatoes","Summer Sausage", "Red Onion"), description = "This is the description",
            type= FoodType.VEGETARIAN))
        foodList.add(Comida(R.drawable.mushroom_burger, "Mushroom Burger", 850,
            ingredients = mutableListOf("Diced Mushrooms","Onion","Egg Replacer", "Pinto Beans"), description = "This is the description",
            type= FoodType.VEGETARIAN))


       // FoodPreferences.add(FoodType.MEDITERRANEAN) //TODO: CHANGE

        // Inflate the layout for this fragment
        var mLinearLayout = inflater.inflate(R.layout.fragment_comida, container, false)
        var comidaListView: ListView = mLinearLayout.findViewById(R.id.listComida)
        var preferenceListView: ListView = mLinearLayout.findViewById(R.id.listOfFavorites)
        var comidaList = GetArrayItems()
        var preferenceList = GetArrayFavorites()
        var comidaAdapter = ComidaAdapter(this.context, comidaList)
        var preferenceAdapter = ComidaAdapter(this.context, preferenceList)
        comidaListView.adapter = comidaAdapter
        preferenceListView.adapter = preferenceAdapter


        comidaListView.onItemClickListener =
            AdapterView.OnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
                var intent = Intent(this.context, GoToEat::class.java)
                intent.putExtra("comida", GetArrayItems().get(i))
                startActivity(intent)
            }

        preferenceListView.onItemClickListener =
            AdapterView.OnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
                var intent = Intent(this.context, GoToEat::class.java)
                intent.putExtra("comida",GetArrayFavorites().get(i))
                startActivity(intent)
            }

        return mLinearLayout
    }

    private fun GetArrayItems(): ArrayList<Comida> {

        var list : ArrayList<Comida> = ArrayList<Comida>()
        for (food in foodList){
            if (food.type !in FoodPreferences){
                list.add(food)
            }
        }
        return list
    }

    private fun GetArrayFavorites(): ArrayList<Comida> {
        var list = ArrayList<Comida>()
        for (food in foodList){
            if (food.type in FoodPreferences){
                list.add(food)
            }
        }
        return list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_comida.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_comida().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}