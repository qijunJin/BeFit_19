package com.example.befit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment

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

        /*
        var mLinearLayout = inflater.inflate(R.layout.fragment_ejercicio, container, false)
        var exerciseListView: ListView = mLinearLayout.findViewById(R.id.listExercise)
        var exerciseList = GetArrayItems()
        var exerciseAdapter = ExerciseAdapter(this.context, exerciseList)
        exerciseListView.adapter = exerciseAdapter
*/
        var mLinearLayout = inflater.inflate(R.layout.fragment_ejercicio, container, false)
        var exerciseListView: ListView = mLinearLayout.findViewById(R.id.listExercise)
        var exerciseList = GetArrayItems()
        var exerciseAdapter = ExerciseAdapter(this.context, exerciseList)
        exerciseListView.adapter = exerciseAdapter

        exerciseListView.onItemClickListener =
            AdapterView.OnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
                var intent = Intent(this.context, GoToGym::class.java)
                intent.putExtra("EXERCISE", exerciseList[i])
                startActivity(intent)
            }

        return mLinearLayout
    }

    private fun GetArrayItems(): ArrayList<Exercise> {
        var listItems = ArrayList<Exercise>()
        listItems.add(Exercise(R.drawable.pushup, "Push up", 20))
        listItems.add(Exercise(R.drawable.lateralraise, "Lateral raise", 12))
        listItems.add(Exercise(R.drawable.concurl, "Concentration curl", 14))
        listItems.add(Exercise(R.drawable.shoulder, "Shoulder press", 12))
        listItems.add(Exercise(R.drawable.barbellcurl, "Barbell curl", 14))
        listItems.add(Exercise(R.drawable.benchpress, "Bench press", 12))
        listItems.add(Exercise(R.drawable.curl, "Preacher curl", 14))
        listItems.add(Exercise(R.drawable.declinepress, "Decline bench press", 12))
        listItems.add(Exercise(R.drawable.dumbbellcurl, "Dumbbell curl", 14))
        listItems.add(Exercise(R.drawable.frontraise, "Front raise", 12))
        listItems.add(Exercise(R.drawable.hammercurl, "Hammer curl", 14))
        listItems.add(Exercise(R.drawable.inclinepress, "Incline bench press", 12))
        return listItems

    }
}
