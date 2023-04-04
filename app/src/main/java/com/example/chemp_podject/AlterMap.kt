package com.example.chemp_podject

import Person
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.example.chemp_podject.databinding.ActivityAlterMapBinding
import com.example.chemp_podject.databinding.ActivityCreateMapBinding
import com.example.chemp_podject.databinding.ActivityHomeBinding
import com.example.chemp_podject.models.PolzovatModel
import org.w3c.dom.Text

class AlterMap : AppCompatActivity() {
    lateinit var binding: ActivityAlterMapBinding
    var index: Int? = null
    lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alter_map)
        binding = ActivityAlterMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val tv = view as TextView
                index = position
                if(position == 0){
                    tv.setTextColor(Color.GRAY)
                }
                else{
                    tv.setTextColor(Color.BLACK)
                }
                if(index != 0){
                    binding.spinnerGender.background = getDrawable(R.drawable.map_input_style)
                }
                if(index == 1){
                    gender = "м"
                }
                if(index == 2){
                    gender = "ж"
                }
                TextChecked()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        Proverka()
        init()
        goActivity()
    }

    private fun goActivity(){
        binding!!.MenuIconAnalizs.setOnClickListener(){
            startActivity(Intent(this@AlterMap, Home::class.java))
        }
    }

    fun Proverka(){
        if(Person.person != null){
            var index = 0
            binding.inputTextF.setText(Person.person!!.F)
            binding.inputTextF.background = getDrawable(R.drawable.map_input_style)
            binding.inputTextI.setText(Person.person!!.I)
            binding.inputTextI.background = getDrawable(R.drawable.map_input_style)
            binding.inputTextO.setText(Person.person!!.O)
            binding.inputTextO.background = getDrawable(R.drawable.map_input_style)
            binding.inputTextBirthday.setText(Person.person!!.Birthday)
            binding.inputTextBirthday.background = getDrawable(R.drawable.map_input_style)
            if(Person.person!!.Gender == "м"){
                index = 1
            }
            if(Person.person!!.Gender == "ж"){
                index = 2
            }
            binding.spinnerGender.setSelection(index)
        }
    }

    fun init(){
        with(binding!!){
            photoProfile.setOnClickListener(){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.type = "image/*"
            }
            inputTextI.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, tart: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                   if(inputTextI.text.isNotEmpty()){
                       inputTextI.background = getDrawable(R.drawable.map_input_style)
                       TextChecked()
                   }
                   else{
                       inputTextI.background = getDrawable(R.drawable.map_input_style_null)
                       TextChecked()
                   }
                }
            })
            inputTextF.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, tart: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextF.text.isNotEmpty()){
                        inputTextF.background = getDrawable(R.drawable.map_input_style)
                        TextChecked()
                    }
                    else{
                        inputTextF.background = getDrawable(R.drawable.map_input_style_null)
                        TextChecked()
                    }
                }
            })
            inputTextO.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, tart: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextO.text.isNotEmpty()){
                        inputTextO.background = getDrawable(R.drawable.map_input_style)
                        TextChecked()
                    }
                    else{
                        inputTextO.background = getDrawable(R.drawable.map_input_style_null)
                        TextChecked()
                    }
                }
            })
            inputTextBirthday.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, tart: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if(inputTextBirthday.text.isNotEmpty()){
                        inputTextBirthday.background = getDrawable(R.drawable.map_input_style)
                        TextChecked()
                    }
                    else{
                        inputTextBirthday.background = getDrawable(R.drawable.map_input_style_null)
                        TextChecked()
                    }
                }
            })

        }
    }

    fun TextChecked()
    {
        if(binding!!.inputTextO.text.isNotEmpty() && binding!!.inputTextI.text.isNotEmpty()
            && binding!!.inputTextF.text.isNotEmpty() && binding!!.inputTextBirthday.text.isNotEmpty() && index != 0){
            binding!!.buttonSave.background = getDrawable(R.drawable.shape_button2)
            binding!!.buttonSave.setOnClickListener{
                Person.person = PolzovatModel(binding.inputTextF.text.toString(), binding.inputTextI.text.toString(),binding.inputTextO.text.toString(),
                binding.inputTextBirthday.text.toString(), gender)
                startActivity(Intent(this@AlterMap, Home::class.java))
                finish()
            }
        }

    }
}
