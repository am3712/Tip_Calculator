package org.hyperskill.calculator.tip

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)
        val slider = findViewById<Slider>(R.id.slider)
        val editText = findViewById<EditText>(R.id.edit_text)

        editText.doAfterTextChanged { text ->
            val enteredValue = text.toString()
            if (enteredValue.isNotEmpty()) {
                textView.visibility = View.VISIBLE
                val tipPercentage = slider.value.toInt()
                textView.text = getString(R.string.tip, tipCal(enteredValue.toFloat(), tipPercentage))
            } else {
                textView.visibility = View.INVISIBLE
            }
        }

        slider.addOnChangeListener { _, value, _ ->
            val enteredValue = editText.text.toString()
            if (enteredValue.isNotEmpty())
                textView.text = getString(R.string.tip, tipCal(enteredValue.toFloat(), value.toInt()))
        }

    }

    private fun tipCal(billValue: Float, tipPercentage: Int): Float {
        return billValue * tipPercentage / 100
    }
}