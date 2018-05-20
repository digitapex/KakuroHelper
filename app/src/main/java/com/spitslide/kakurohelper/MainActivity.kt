package com.spitslide.kakurohelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cellsPicker : NumberPicker = findViewById(R.id.cells_picker)

        cellsPicker.minValue = 2
        cellsPicker.maxValue = 15
        cellsPicker.wrapSelectorWheel = false

        cellsPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener{
            override fun onValueChange(p0: NumberPicker?, oldVal: Int, newVal: Int) {
                Toast.makeText(this@MainActivity, "" + newVal, Toast.LENGTH_SHORT).show()
            }
        })

        val databaseUtil : DatabaseUtil = DatabaseUtil(this)

        val inputStream : InputStream = resources.openRawResource(R.raw.data)
        val br = BufferedReader(InputStreamReader(inputStream))
        var strLine: String? = null

        while ({ strLine = br.readLine(); strLine }() != null) {
//            System.out.println(strLine);
            val parts : List<String>? = strLine?.split("\t")
            databaseUtil.insertRow(parts?.get(0), parts?.get(1), parts?.get(2))
//            System.out.println(parts?.get(0))
        }

        //Close the input stream
        br.close()

    }
}
