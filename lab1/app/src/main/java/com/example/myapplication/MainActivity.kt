package com.example.myapplication

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.Btn)
        val et = findViewById<EditText>(R.id.pwd)
        val tv = findViewById<TextView>(R.id.textView)
        val rg = findViewById<RadioGroup>(R.id.radiogroup)


        rg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radiobutton1 -> et.transformationMethod = HideReturnsTransformationMethod.getInstance()
                R.id.radiobutton2 -> et.transformationMethod = PasswordTransformationMethod.getInstance()

            }
        }

        button.setOnClickListener{
            tv.text = ""
            if(et.text.toString().isNotEmpty())
            tv.text = et.text
            else{
                val myToast = Toast.makeText(this, "Заполните поле пароля", Toast.LENGTH_SHORT)
                myToast.show()
            }
        }
    }
}
