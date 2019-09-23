package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var nStr: String = ""
    private val nList = ArrayList<Double>()  // 数式に含まれる数を保持する配列
    private val oList = ArrayList<Char>()    // 数式に含まれるオペレーション(四則演算)を保持する配列

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0.setOnClickListener {
            formula.text = "${formula.text}0"
            nStr += "0"
        }
        num1.setOnClickListener {
            formula.text = "${formula.text}1"
            nStr += "1"
        }
        num2.setOnClickListener {
            formula.text = "${formula.text}2"
            nStr += "2"
        }
        num3.setOnClickListener {
            formula.text = "${formula.text}3"
            nStr += "3"
        }
        num4.setOnClickListener {
            formula.text = "${formula.text}4"
            nStr += "4"
        }
        num5.setOnClickListener {
            formula.text = "${formula.text}5"
            nStr += "5"
        }
        num6.setOnClickListener {
            formula.text = "${formula.text}6"
            nStr += "6"
        }
        num7.setOnClickListener {
            formula.text = "${formula.text}7"
            nStr += "7"
        }
        num8.setOnClickListener {
            formula.text = "${formula.text}8"
            nStr += "8"
        }
        num9.setOnClickListener {
            formula.text = "${formula.text}9"
            nStr += "9"
        }
        point.setOnClickListener {
            formula.text = "${formula.text}."
            nStr += "."
        }

        add.setOnClickListener {
            formula.text = "${formula.text}+"
            addList(nStr, '+')                 //後述、nStrを小数に変換しnListに入れ、"+"をoListに入れる。
            nStr = ""                               //nStrを空に戻す
        }
        subtract.setOnClickListener {
            formula.text = "${formula.text}-"
            addList(nStr, '-')                 //後述、nStrを小数に変換しnListに入れ、"+"をoListに入れる。
            nStr = ""                               //nStrを空に戻す
        }
        multiply.setOnClickListener {
            formula.text = "${formula.text}*"
            addList(nStr, '*')                 //後述、nStrを小数に変換しnListに入れ、"+"をoListに入れる。
            nStr = ""                               //nStrを空に戻す
        }
        divide.setOnClickListener {
            formula.text = "${formula.text}/"
            addList(nStr, '/')                 //後述、nStrを小数に変換しnListに入れ、"+"をoListに入れる。
            nStr = ""                               //nStrを空に戻す
        }

        equal.setOnClickListener {
            if (nList.size != (oList.size + 1)) return@setOnClickListener

            formula.text = "${formula.text}="
            addList(nStr, '=')
            var result = calculate().toString()
            formula.text = result
            nStr = result
            nList.clear()
            oList.clear()
        }

        memo.setOnClickListener {}
        sign.setOnClickListener {}

        clear.setOnClickListener {
            formula.text = ""
            nStr = ""
            nList.clear()
            oList.clear()
        }

        delete.setOnClickListener {
            var formulaStr = formula.text.toString()
            if (formulaStr.isNotEmpty()) {
                formula.text = formulaStr.subSequence(0, formulaStr.lastIndex)
            }
            if (nStr.isNotEmpty()) {
                nStr = nStr.substring(0, nStr.lastIndex)
            }
        }
    }

    private fun addList(str: String, ope: Char) {
        try {
            var num = str.toDouble()
            nList.add(num)
            if (ope != '=') oList.add(ope)
        } catch (e: Exception) {
            formula.text = "Numeric error"
        }
    }

//    fun addList(str: String) {
//        try {
//            var num = str.toDouble()
//            nList.add(num)
//        } catch (e: Exception) {
//            formula.text = "Numeric error"
//        }
//    }

    private fun calculate(): Double {
        var i = 0
        while (i < oList.size) {
            if (oList.elementAt(i) == '*' || oList.elementAt(i) == '/') {
                var result =
                    if (oList.elementAt(i) == '*') nList.elementAt(i) * nList.elementAt(i+1)
                    else nList.elementAt(i) / nList.elementAt(i+1)
                nList[i] = result
                nList.removeAt(i+1)
                oList.removeAt(i)
                i--
            }

            else if(oList.elementAt(i) == '-'){
                oList[i] = '+'
                nList[i+1] = nList.elementAt(i+1) * -1
            }
            i++
        }

        var result = 0.0
        for (i in nList){
            result += i
        }
        return result
    }
}
