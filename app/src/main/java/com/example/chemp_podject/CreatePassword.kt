package com.example.chemp_podject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chemp_podject.databinding.ActivityCreatePasswordBinding

class CreatePassword : AppCompatActivity() {
    private var password: String? = null
    private var index: Int = 0
    private var binding: ActivityCreatePasswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePasswordBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }
    private fun init()
    {
        with(binding!!)
        {
            buttonCircle1.setOnClickListener() {
                SumPassword(buttonCircle1.text.toString())
            }
            buttonCircle2.setOnClickListener() {
                SumPassword(buttonCircle2.text.toString())
            }
            buttonCircle3.setOnClickListener() {
                SumPassword(buttonCircle3.text.toString())
            }
            buttonCircle4.setOnClickListener() {
                SumPassword(buttonCircle4.text.toString())
            }
            buttonCircle5.setOnClickListener() {
                SumPassword(buttonCircle5.text.toString())
            }
            buttonCircle6.setOnClickListener() {
                SumPassword(buttonCircle6.text.toString())
            }
            buttonCircle7.setOnClickListener() {
                SumPassword(buttonCircle7.text.toString())
            }
            buttonCircle8.setOnClickListener() {
                SumPassword(buttonCircle8.text.toString())
            }
            buttonCircle9.setOnClickListener() {
                SumPassword(buttonCircle9.text.toString())
            }
            buttonCircle0.setOnClickListener() {
                SumPassword(buttonCircle0.text.toString())
            }
            buttonClean.setOnClickListener(){
                if(index in 1..3){
                    password = password?.dropLast(1)
                    index--
                    SetPassword(index)
                }
            }
        }
    }

    fun SumPassword(Password: String){
        if(index in 0..3) {
            password += Password
            index++
            SetPassword(index)
        }
        if(index == 4){
            startActivity(Intent(this@CreatePassword, CreateMap::class.java))
        }
    }

    private fun SetPassword(point: Int){
        with(binding!!){
            when(point){
                0 -> {
                    point1.background = getDrawable(R.drawable.point_style_stroke)
                    point2.background = getDrawable(R.drawable.point_style_stroke)
                    point3.background = getDrawable(R.drawable.point_style_stroke)
                    point4.background = getDrawable(R.drawable.point_style_stroke)
                }
                1 -> {
                    point1.background = getDrawable(R.drawable.point_style_blue)
                    point2.background = getDrawable(R.drawable.point_style_stroke)
                    point3.background = getDrawable(R.drawable.point_style_stroke)
                    point4.background = getDrawable(R.drawable.point_style_stroke)
                }
                2 -> {
                    point1.background = getDrawable(R.drawable.point_style_blue)
                    point2.background = getDrawable(R.drawable.point_style_blue)
                    point3.background = getDrawable(R.drawable.point_style_stroke)
                    point4.background = getDrawable(R.drawable.point_style_stroke)
                }
                3 -> {
                    point1.background = getDrawable(R.drawable.point_style_blue)
                    point2.background = getDrawable(R.drawable.point_style_blue)
                    point3.background = getDrawable(R.drawable.point_style_blue)
                    point4.background = getDrawable(R.drawable.point_style_stroke)
                }
                4 -> {
                    point1.background = getDrawable(R.drawable.point_style_blue)
                    point2.background = getDrawable(R.drawable.point_style_blue)
                    point3.background = getDrawable(R.drawable.point_style_blue)
                    point4.background = getDrawable(R.drawable.point_style_blue)
                }
            }
        }
    }
}

