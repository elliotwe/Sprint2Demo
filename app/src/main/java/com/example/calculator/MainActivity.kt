package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var res = 0.0
    private var curVal = ""
    private var performOp = false
    private var op = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)

        val btnEqual = findViewById<Button>(R.id.btnenter)

        val btnMultiply = findViewById<Button>(R.id.btnmultiply)
        val btnDivide = findViewById<Button>(R.id.btndivide)
        val btnAdd = findViewById<Button>(R.id.btnadd)
        val btnSubtract = findViewById<Button>(R.id.btnsubtract)

        val btnDot = findViewById<Button>(R.id.btndot)
        val btnBackspace = findViewById<Button>(R.id.btnbackspace)
        val btnClear = findViewById<Button>(R.id.btnclear)

        val btnOne = findViewById<Button>(R.id.btnnum1)
        val btnTwo = findViewById<Button>(R.id.btnnum2)
        val btnThree = findViewById<Button>(R.id.btnnum3)
        val btnFour = findViewById<Button>(R.id.btnnum4)
        val btnFive = findViewById<Button>(R.id.btnnum5)
        val btnSix = findViewById<Button>(R.id.btnnum6)
        val btnSeven = findViewById<Button>(R.id.btnnum7)
        val btnEight = findViewById<Button>(R.id.btnnum8)
        val btnNine = findViewById<Button>(R.id.btnnum9)
        val btnZero = findViewById<Button>(R.id.btnnum0)

        btnAdd.setOnClickListener {
            performOperation("+")
        }

        btnSubtract.setOnClickListener {
            performOperation("-")
        }

        btnMultiply.setOnClickListener {
            performOperation("*")
        }

        btnDivide.setOnClickListener {
            performOperation("/")
        }

        btnDot.setOnClickListener {
            addDigit(".")
        }

        btnEqual.setOnClickListener {
            // only allow equal size operator to work if two numbers have being operated on
            if (op != "")
            {
                val resValue = getRes()
                updateResWithOp(op, resValue)
                op = ""
            }
        }

        btnBackspace.setOnClickListener {
            val txtResult = findViewById<TextView>(R.id.txtresult)

            var newRes = txtResult.text.toString()

            if (newRes != "0")
            {
                newRes = newRes.substring(0, newRes.length - 1)

                if (newRes == "")
                {
                    newRes = "0"
                }

                txtResult.text = newRes
            }
        }

        btnClear.setOnClickListener {
            val txtResult = findViewById<TextView>(R.id.txtresult)

            txtResult.text = "0"
            res = 0.0
            op = ""
            performOp = false
        }

        btnOne.setOnClickListener {
            addDigit("1")
        }

        btnTwo.setOnClickListener {
            addDigit("2")
        }

        btnThree.setOnClickListener {
            addDigit("3")
        }

        btnFour.setOnClickListener {
            addDigit("4")
        }

        btnFive.setOnClickListener {
            addDigit("5")
        }

        btnSix.setOnClickListener {
            addDigit("6")
        }

        btnSeven.setOnClickListener {
            addDigit("7")
        }

        btnEight.setOnClickListener {
            addDigit("8")
        }

        btnNine.setOnClickListener {
            addDigit("9")
        }

        btnZero.setOnClickListener {

            addDigit("0")
        }

    }

    fun getRes(): Double {
        val txtResult = findViewById<TextView>(R.id.txtresult)

        var resStr = txtResult.text.toString()

        return resStr.toDouble()
    }

    fun updateResWithOp(opPressed:String, resValue:Double)
    {
        val txtResult = findViewById<TextView>(R.id.txtresult)

        if (opPressed == "/")
        {
            res /= resValue
        }
        else if (opPressed == "*")
        {
            res *= resValue
        }
        else if (opPressed == "+")
        {
            res += resValue
        }
        else if (opPressed == "-")
        {
            res -= resValue
        }

        if (res.toInt() - res == 0.0)
        {
            // if the result is a whole number display as such without the decimal point
            txtResult.text = res.toInt().toString()
        }
        else
        {
            txtResult.text = res.toString()
        }

        performOp = true
    }

    fun performOperation(opPressed:String)
    {
        val resValue = getRes()

        if (op == "")
        {
            // if first operation is being performed store the number but don't preform the calculation
            res = resValue
            performOp = true
        }
        else
        {
            updateResWithOp(op, resValue)
        }

        op = opPressed

    }

    fun addDigit(digit : String)
    {
        val txtResult = findViewById<TextView>(R.id.txtresult)

        var res = txtResult.text.toString()

        // prevent multiple decimal points from being added
        if (digit == "." && res.contains('.'))
        {
            return
        }
        // if result is zero or a operation has been performed reset the number
        else if (res == "0" && digit != "." || performOp)
        {
            if (digit == ".")
            {
                txtResult.text = "0." // make just a decimal point a valid number
            }
            else
            {
                txtResult.text = digit
            }

            performOp = false
        }
        else
        {
            res = res.plus(digit)

            txtResult.text = res
        }
    }
}