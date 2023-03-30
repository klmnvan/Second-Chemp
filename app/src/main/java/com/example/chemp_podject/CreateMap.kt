package com.example.chemp_podject

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chemp_podject.databinding.ActivityCreateMapBinding
import com.example.chemp_podject.models.PolzovatModel


class
CreateMap : AppCompatActivity() {
    lateinit var binding: ActivityCreateMapBinding
    var index: Int = 0
    var person: PolzovatModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMapBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                val tv = view as TextView
                index = position
                tv.setTextSize(14F)
                if (position === 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                if(index != 0)
                {
                    binding.spinnerGender.background = getDrawable(R.drawable.map_input_style)
                }
                textChecked()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        init()

    }
    fun init(){
    binding.inputTextName.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            binding.inputTextName.background = getDrawable(R.drawable.map_input_style)
            person?.I = binding.inputTextName.text.toString()
            Log.d(TAG, person?.I.toString())
            textChecked()
        }
    })
        binding.inputTextPatronymic.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.inputTextPatronymic.background = getDrawable(R.drawable.map_input_style)
                person?.O = binding.inputTextPatronymic.text.toString()
                textChecked()
            }
        })
        binding.inputTextSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.inputTextSurname.background = getDrawable(R.drawable.map_input_style)
                person?.F = binding.inputTextSurname.text.toString()
                textChecked()
            }
        })
        binding.inputTextBirthday.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.inputTextBirthday.background = getDrawable(R.drawable.map_input_style)
                person?.Birthday = binding.inputTextBirthday.text.toString()
                textChecked()
            }
        })
    }

    fun textChecked()
    {
        if(binding!!.inputTextName.text.isNotEmpty() && binding!!.inputTextPatronymic.text.isNotEmpty()
            && binding!!.inputTextSurname.text.isNotEmpty() && binding!!.inputTextBirthday.text.isNotEmpty() && index != 0){
            binding!!.buttonCreate.background = getDrawable(R.drawable.shape_button2)
            binding!!.buttonCreate.setOnClickListener{
                person = PolzovatModel(binding.inputTextName.text.toString(),binding.inputTextName.text.toString(),binding.inputTextName.text.toString(),binding.inputTextName.text.toString(),binding.inputTextName.text.toString())
                Log.d(TAG, binding.inputTextName.toString())
                startActivity(Intent(this@CreateMap, Home::class.java))
            }
        }
    }

}

/*with(binding!!){
    inputTextName.background = getDrawable(R.drawable.map_input_style)
    inputTextPatronymic.background = getDrawable(R.drawable.map_input_style)
    inputTextSurname.background = getDrawable(R.drawable.map_input_style)
    inputTextBirthday.background = getDrawable(R.drawable.map_input_style)
}*/

