package com.spitslide.kakurohelper

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader



class MainActivity : AppCompatActivity() {

    lateinit var cellsPicker : NumberPicker
    lateinit var sumPicker : NumberPicker
    lateinit var numbersList : TextView
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
//                Toast.makeText(this@MainActivity, "" + newVal, Toast.LENGTH_SHORT).show()
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
//                Toast.makeText(this@MainActivity, "" + newVal, Toast.LENGTH_SHORT).show()

                val cellsValue: Int = cellsPicker.value
                displayNumbers(cellsValue, newSumVal)
            }
        })




//        val databaseUtil : DatabaseUtil = DatabaseUtil(this)
//
//        val inputStream : InputStream = resources.openRawResource(R.raw.data)
//        val br = BufferedReader(InputStreamReader(inputStream))
//        var strLine: String? = null
//
//        while ({ strLine = br.readLine(); strLine }() != null) {
////            System.out.println(strLine);
//            val parts : List<String>? = strLine?.split("\t")
//            databaseUtil.insertRow(parts?.get(0), parts?.get(1), parts?.get(2))
////            System.out.println(parts?.get(0))
//        }
//
//        //Close the input stream
//        br.close()

    }

        fun displayNumbers(cellsValue: Int, sumValue: Int){
            val databaseAssetHelper: DatabaseAssetHelper = DatabaseAssetHelper(this)
            val numbers: String = databaseAssetHelper.getRow(cellsValue, sumValue)
//            val maxValue: String = databaseAssetHelper.getMax(cellsValue)
//            Toast.makeText(this, numbers, Toast.LENGTH_SHORT).show()
//            numbers = numbers.replace(" ", "\n")
//            numbersList.text = numbers
            val newNumbers: MutableList<String> = numbers.split(" ").toMutableList()
//            possibleNumbers = newNumbers

            for(i in newNumbers.indices){
                newNumbers[i] = newNumbers[i].replace("\\B", " ")
            }

            arrayAdapter.clear()
            arrayAdapter.addAll(newNumbers)


        }

    fun changeValue(view: View){
        sumPicker.minValue = 3
        sumPicker.maxValue = 25
        sumPicker.wrapSelectorWheel = false
//        sumPicker.displayedValues = arrayOf(3,4,5)
    }

    fun setNewSumRange(newCellsVal: Int){
        val databaseAssetHelper: DatabaseAssetHelper = DatabaseAssetHelper(this)
        val minMax: ArrayList<String> = databaseAssetHelper.getMinMax(newCellsVal)
        sumPicker.minValue = minMax[0].toInt()
        sumPicker.maxValue = minMax[1].toInt()
        sumPicker.wrapSelectorWheel = false
    }
}
