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
 * Use the [fragmentEjercicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentEjercicio : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var mLinearLayout = inflater.inflate(R.layout.fragment_ejercicio, container, false)
        var exerciseListView: ListView = mLinearLayout.findViewById(R.id.listExercise)
        var exerciseList = GetArrayItems()
        var exerciseAdapter = ExerciseAdapter(this.context, exerciseList)
        exerciseListView.adapter = exerciseAdapter

        return mLinearLayout
    }S

    private fun GetArrayItems(): ArrayList<Exercise> {
        var listItems = ArrayList<Exercise>()
        listItems.add(Exercise(R.drawable.pushup, "push up"))
        listItems.add(Exercise(R.drawable.lateralraise, "lateral raise"))
        listItems.add(Exercise(R.drawable.pushup, "push up"))
        listItems.add(Exercise(R.drawable.lateralraise, "lateral raise"))
        listItems.add(Exercise(R.drawable.pushup, "push up"))
        listItems.add(Exercise(R.drawable.lateralraise, "lateral raise"))
        listItems.add(Exercise(R.drawable.pushup, "push up"))
        listItems.add(Exercise(R.drawable.lateralraise, "lateral raise"))
        return listItems
    }
}
