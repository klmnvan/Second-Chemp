package com.example.chemp_podject

import Person
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
import com.example.chemp_podject.api.ApiRequestBlock
import com.example.chemp_podject.databinding.ActivityCreateMapBinding
import com.example.chemp_podject.models.PolzovatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class
CreateMap : AppCompatActivity() {
    lateinit var binding: ActivityCreateMapBinding
    var index: Int = 0
    lateinit var gender: String


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
                if(index == 1 ){
                    gender = "м"
                }
                if(index == 2)
                {
                    gender = "ж"
                }
                textChecked()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
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
            Person.person?.firstname = binding.inputTextName.text.toString()
            Log.d(TAG,  Person.person?.firstname.toString())
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
                Person.person?.middlename = binding.inputTextPatronymic.text.toString()
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
                Person.person?.lastname = binding.inputTextSurname.text.toString()
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
                Person.person?.bith = binding.inputTextBirthday.text.toString()
                textChecked()
            }
        })
        binding.buttonPropusk.setOnClickListener(){
            startActivity(Intent(this@CreateMap, Home::class.java))
            finish()
        }
    }

    fun textChecked()
    {
        if(binding!!.inputTextName.text.isNotEmpty() && binding!!.inputTextPatronymic.text.isNotEmpty()
            && binding!!.inputTextSurname.text.isNotEmpty() && binding!!.inputTextBirthday.text.isNotEmpty() && index != 0){
            binding!!.buttonCreate.background = getDrawable(R.drawable.shape_button2)
            binding!!.buttonCreate.setOnClickListener{
                Person.person = PolzovatModel(0,binding.inputTextSurname.text.toString(),binding.inputTextName.text.toString(),binding.inputTextPatronymic.text.toString(),
                    binding.inputTextBirthday.text.toString(), "Мужской","1")
                val intent = Intent(this@CreateMap, Home::class.java)
                Log.d(TAG, Person.person!!.pol)
                //postData()
                startActivity(intent)
            }
        }
    }
    /*fun postData(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://medic.madskill.ru")
            .client(httpClient)
            .build()
        val requestApi = api.create(ApiRequestBlock::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                //val response = requestApi.postProfile(Person.person!!)
                Log.d(TAG,"Success")
            }catch (e: Exception){
                Log.d(TAG, e.toString())
            }
        }
    }*/

}

/*with(binding!!){
    inputTextName.background = getDrawable(R.drawable.map_input_style)
    inputTextPatronymic.background = getDrawable(R.drawable.map_input_style)
    inputTextSurname.background = getDrawable(R.drawable.map_input_style)
    inputTextBirthday.background = getDrawable(R.drawable.map_input_style)
}*/

