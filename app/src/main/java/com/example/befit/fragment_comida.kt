package com.example.befit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mLinearLayout = inflater.inflate(R.layout.fragment_comida, container, false)
        var comidaListView: ListView = mLinearLayout.findViewById(R.id.listComida)
        var comidaList = GetArrayItems()
        var comidaAdapter = ComidaAdapter(this.context, comidaList)
        comidaListView.adapter = comidaAdapter

        return mLinearLayout
    }

    private fun GetArrayItems(): ArrayList<Comida> {
        var listItems = ArrayList<Comida>()
        //TODO poner imagenes correctas
        listItems.add(Comida(R.drawable.lateralraise, "Seafood Paella", 379))
        listItems.add(Comida(R.drawable.lateralraise, "Backed Chicken", 340))
        listItems.add(Comida(R.drawable.lateralraise, "Spring Rolls", 154))
        listItems.add(Comida(R.drawable.lateralraise, "Ramen", 200))
        listItems.add(Comida(R.drawable.lateralraise, "Cobb Salad", 400))
        listItems.add(Comida(R.drawable.lateralraise, "Fried Chicken", 190))
        listItems.add(Comida(R.drawable.lateralraise, "Kibbeh", 365))
        listItems.add(Comida(R.drawable.lateralraise, "Nachos", 346))
        listItems.add(Comida(R.drawable.lateralraise, "Chicken Tamales", 141))
        listItems.add(Comida(R.drawable.lateralraise, "Pasta Salad", 356))
        listItems.add(Comida(R.drawable.lateralraise, "Mushroom Burger", 850))
        return listItems
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
