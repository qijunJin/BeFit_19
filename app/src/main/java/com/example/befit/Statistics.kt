package com.example.befit

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_statistics.*

class Statistics : AppCompatActivity(), OnChartValueSelectedListener {

    private val TAG : String = "StatisticsActivity"

    // PieChart example
    lateinit var chart : PieChart
    //Chart values:
    private var yData : MutableList<Float> = mutableListOf<Float>(25.3f, 23.4f, 16.3f, 14.15f)
    private var xData : MutableList<String> = mutableListOf<String>("Albert","Quijun","Oscar","Marta")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        //Creating chart
        Log.d(TAG,"onCreate: starting to create chart")

        chart = findViewById(R.id.pieChart_test)

        //Should use chart.description
        chart.contentDescription = "ContentDescription Test"
        chart.isRotationEnabled = true

        //chart.setUsePercentValues(true)
        //chart.setHoleColor(Color.)
        //chart.setCenterTextColor(Color.)
        chart.holeRadius = 25f
        chart.setTransparentCircleAlpha(0);
        chart.centerText = "Chart Text Test"
        chart.setCenterTextSize(10f)
        chart.setDrawEntryLabels(true)
        //chart.setEntryLabelTextSize(20f)
        // etc...

        addDataSet()


        //Listeners
        chart.setOnChartValueSelectedListener(this)

    }

    private fun addDataSet() {
        Log.d(TAG,"setDataSet: Started")
        var yEntrys : MutableList<PieEntry> = mutableListOf()
        var xEntrys : MutableList<String> = xData

        //Init yEntrys
        for(entry in yData){
            yEntrys.add(PieEntry(entry))
        }

        var pieDataSet : PieDataSet = PieDataSet(yEntrys,"Chart Test")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 20f

        //Colors of the DataSet
        var colorList = mutableListOf<Int>()
        colorList.add(Color.RED)
        colorList.add(Color.YELLOW)
        colorList.add(Color.BLUE)
        colorList.add(Color.GREEN)

        pieDataSet.setColors(colorList)

        //Add legend to chart
        var legend : Legend = chart.legend
        legend.form = Legend.LegendForm.CIRCLE
        // legend.setPosition() dosn't exist anymore

        //Create PieDataObject
        var pieData : PieData = PieData(pieDataSet)
        chart.data = pieData
        chart.invalidate()
    }

    //Chart Listeners
    override fun onNothingSelected() {
        Log.d(TAG,"onNothingSelected from Chart")
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.d(TAG, "onValueSelectedFromChart")

        Log.d(TAG, "onValueSelected" + e.toString())
        Log.d(TAG, "onValueSelected" + h.toString())

        val pos1: Int = e.toString().indexOf("y: ")
        val numberExtracted : String = e.toString().substring(pos1+3)

        val stringRelated : String = xData.get( yData.indexOf(numberExtracted.toFloat()) )

        Toast.makeText(this, "Selected: $stringRelated: $numberExtracted",Toast.LENGTH_LONG ).show()

    }
}
