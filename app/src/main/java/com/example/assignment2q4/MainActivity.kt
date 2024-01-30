package com.example.assignment2q4

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

var operation = ""

class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        // make toast
//        Toast.makeText(this, parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show()
        operation = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // make toast
        Toast.makeText(this, "Please select", Toast.LENGTH_LONG).show()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number1 = findViewById<android.widget.EditText>(R.id.number1)
        var number2 = findViewById<android.widget.EditText>(R.id.number2)
        var answer = findViewById<android.widget.TextView>(R.id.answer)

        var button = findViewById<android.widget.Button>(R.id.button)

        number1.setText("0")
        number2.setText("2")
        answer.setText("")

        // https://developer.android.com/develop/ui/views/components/spinner?hl=zh-cn
        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(this,
            R.array.spinner_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }.also {
            spinner.onItemSelectedListener = SpinnerActivity()
        }

        //set listener to button
        button.setOnClickListener {
            var num1 = number1.text.toString().toDouble()
            var num2 = number2.text.toString().toDouble()
            var ans = 0.0
            var operation = operation
            // make toast
//            Toast.makeText(this, num1.toString()+" "+num2.toString()+ "  "+ operation, Toast.LENGTH_LONG).show()

            if (operation == "+") {
                ans = num1 + num2
            } else if (operation == "-") {
                ans = num1 - num2
            } else if (operation == "*") {
                ans = num1 * num2
            } else if (operation == "/") {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                ans = 0.0
            } else if (operation == "%") {
                ans = num1 % num2
            }

            answer.setText(ans.toString())
        }


    }

}

