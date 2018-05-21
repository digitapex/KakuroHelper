package com.spitslide.kakurohelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*



class MainActivity : AppCompatActivity() {

    lateinit var cellsPicker : NumberPicker
    lateinit var sumPicker : NumberPicker
    lateinit var listView : ListView
    lateinit var possibleNumbers : ArrayList<String>
    lateinit var arrayAdapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cellsPicker = findViewById(R.id.cells_picker)
        sumPicker = findViewById(R.id.sum_picker)
        listView = findViewById(R.id.list_view)
        possibleNumbers = arrayListOf("1 2")
        arrayAdapter = ArrayAdapter(this, R.layout.text_in_list, R.id.text1, possibleNumbers)
        listView.adapter = arrayAdapter

        cellsPicker.minValue = 2
        cellsPicker.maxValue = 9
        cellsPicker.wrapSelectorWheel = false

        cellsPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener{
            override fun onValueChange(p0: NumberPicker?, oldVal: Int, newCellsVal: Int) {
                setNewSumRange(newCellsVal)
                val sumValue: Int = sumPicker.value
                displayNumbers(newCellsVal, sumValue)
            }
        })

        sumPicker.minValue = 3
        sumPicker.maxValue = 45
        sumPicker.wrapSelectorWheel = false

        sumPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener{
            override fun onValueChange(p0: NumberPicker?, oldVal: Int, newSumVal: Int) {
                val cellsValue: Int = cellsPicker.value
                displayNumbers(cellsValue, newSumVal)
            }
        })
    }

        fun displayNumbers(cellsValue: Int, sumValue: Int){
            val databaseAssetHelper = DatabaseAssetHelper(this)
            val numbers: String = databaseAssetHelper.getRow(cellsValue, sumValue)
            val newNumbers: MutableList<String> = numbers.split(" ").toMutableList()

            // we add whitespace between individual numbers to be easier to read
            for(i in newNumbers.indices){
                val sb = StringBuilder()
                for(c in newNumbers[i].toCharArray()){
                    sb.append(c).append(" ")
                }
                newNumbers[i] = sb.toString()
            }

            arrayAdapter.clear()
            arrayAdapter.addAll(newNumbers)


        }


    fun setNewSumRange(newCellsVal: Int){
        val databaseAssetHelper = DatabaseAssetHelper(this)
        val minMax: ArrayList<String> = databaseAssetHelper.getMinMax(newCellsVal)

        // on KitKat wrapping reappears after setting min and max values, even when using wrapSelectorWheel = false
        // so we recreate displayedValues, which solves the problem
        // https://stackoverflow.com/a/24963508/9702500
        sumPicker.displayedValues = null
        sumPicker.minValue = minMax[0].toInt()
        sumPicker.maxValue = minMax[1].toInt()
        sumPicker.wrapSelectorWheel = false
        val newArray = (minMax[0].toInt()..minMax[1].toInt())
        sumPicker.displayedValues = newArray.map { it.toString() }.toTypedArray()
    }
}
