package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    private var state: Operator = Operator.Add(0.0)

    private val displayValue: Double
        get() = when (resultTextView.text.toString()) {
            "" -> 0.0
            else -> resultTextView.text.toString().toDouble()
        }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button0.setOnClickListener { operator(button0.text as String) }

        button1.setOnClickListener { operator(button1.text as String) }

        button2.setOnClickListener { operator(button2.text as String) }

        button3.setOnClickListener { operator(button3.text as String) }

        button4.setOnClickListener { operator(button4.text as String) }

        button5.setOnClickListener { operator(button5.text as String) }

        button6.setOnClickListener { operator(button6.text as String) }

        button7.setOnClickListener { operator(button7.text as String) }

        button8.setOnClickListener { operator(button8.text as String) }

        button9.setOnClickListener { operator(button9.text as String) }

        buttonEqual.setOnClickListener { operator(buttonEqual.text as String) }

        buttonDel.setOnClickListener {
            if (resultTextView.text != "")
                resultTextView.text = resultTextView.text.subSequence(0, resultTextView.text.lastIndex)
        }
        buttonDel.setOnLongClickListener {
            operator(buttonDel.text as String)
            return@setOnLongClickListener false
        }

        buttonNegate.setOnClickListener { operator(buttonNegate.text as String) }

        buttonPercent.setOnClickListener { operator(buttonPercent.text as String) }

        buttonDivide.setOnClickListener { operator(buttonDivide.text as String) }

        buttonMultiply.setOnClickListener { operator(buttonMultiply.text as String) }

        buttonMinus.setOnClickListener { operator(buttonMinus.text as String) }

        buttonPlus.setOnClickListener { operator(buttonPlus.text as String) }

        buttonComma.setOnClickListener { resultTextView.text = resultTextView.text.toString() + "." }
    }


    private fun onAction(fn: Operator) {
        state = fn
        resultTextView.text = ""
    }

    @SuppressLint("SetTextI18n")
    private fun operator(x: String) {
        if (Regex("[0-9.]").matches(x)) {
            resultTextView.text = resultTextView.text.toString() + x
        } else {
            when (x) {
                "+" -> {
                    onAction(Operator.Add(displayValue))
                }

                "-" -> {
                    onAction(Operator.Sub(displayValue))
                }

                "X" -> {
                    onAction((Operator.Mult(displayValue)))
                }

                "/" -> {
                    onAction(Operator.Div(displayValue))
                }

                "%" -> {
                    onAction(Operator.Add(displayValue / 100))
                    operator("=")
                }

                "DEL" -> onAction(Operator.Add(0.0))

                "+/-" -> {
                    onAction(Operator.Add(-1 * displayValue))
                    operator("=")
                }

                "=" -> {
                    val res = state.calculate(displayValue)

                    if (res == floor(res)) {
                        resultTextView.text = (res.toInt()).toString()
                    } else {
                        resultTextView.text = res.toString()
                    }

                }
            }
        }
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
