package com.example.befit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_main_interface.*
import kotlinx.android.synthetic.main.activity_statistics.*


class Statistics : AppCompatActivity(), OnChartValueSelectedListener {

    private val TAG : String = "StatisticsActivity"

    // PieChart example
    lateinit var chart : LineChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)


        if(MainActivity.user_actual.genere == "home"){
            img_genere.setImageResource(R.drawable.male_round)
        }else if(MainActivity.user_actual.genere == "dona"){
            img_genere.setImageResource(R.drawable.female_round)
        }else{
            img_genere.setImageResource(R.drawable.male_round) //Per defecte carregem la iamtge de home, en cas que hi hagi algun error, pero no deuria pasar
        }
        //Creating chart
        Log.d(TAG,"onCreate: starting to create chart")

        chart = findViewById(R.id.LineChart)
        var lineDataSet1 : LineDataSet = LineDataSet(dataValues(), "User Progress (kcal)")
        var dataSets :ArrayList<ILineDataSet> = arrayListOf()
        dataSets.add(lineDataSet1)

        var data : LineData = LineData(dataSets)
        data.setValueTextSize(12f)
        chart.data = data
        chart.description.isEnabled = false
        chart.invalidate()

        //Listeners
        //chart.setOnChartValueSelectedListener(this)

    }

    private fun dataValues() : ArrayList<Entry>{
         var dataVals : ArrayList<Entry> = arrayListOf()
        dataVals.add(Entry(1f, 20f))
        dataVals.add(Entry(2f, 32f))
        dataVals.add(Entry(3f, 52f))
        dataVals.add(Entry(4f, 100f))
        dataVals.add(Entry(5f, 50f))
        dataVals.add(Entry(6f, 80f))
        dataVals.add(Entry(7f, 120f))

        return dataVals
    }

    //Chart Listeners
    override fun onNothingSelected() {
        Log.d(TAG,"onNothingSelected from Chart")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }
}
