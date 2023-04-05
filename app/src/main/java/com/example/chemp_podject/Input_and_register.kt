package com.example.chemp_podject

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.Nullable
import androidx.core.widget.addTextChangedListener
import com.example.chemp_podject.api.ApiRequestBlock
import com.example.chemp_podject.databinding.ActivityInputAndRegisterBinding
import com.example.chemp_podject.databinding.ActivityOnBoard1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class Input_and_register : AppCompatActivity() {
    private var binding: ActivityInputAndRegisterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_and_register)
        binding = ActivityInputAndRegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        TextChanged()
        init()
    }
    fun init()
    {
        binding!!.buttonDalee.setOnClickListener()
        {
            if(binding!!.inputEmailtext.text.isNotEmpty())
            {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
                val retrofit =
                    Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://medic.madskill.ru/")
                        .client(httpClient)
                        .build()
                val requestApi = retrofit.create(ApiRequestBlock::class.java)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response = requestApi.postEmail(binding!!.inputEmailtext.text.toString()).awaitResponse()
                        Log.d("Response","success")

                    } catch (e: Exception) {
                        Log.d(ContentValues.TAG, e.toString())
                    }
                }
                var intent = Intent(this@Input_and_register, CodFromEmail::class.java)
                intent.putExtra("email", binding!!.inputEmailtext.text.toString())
                startActivity(intent)
            }
        }

    }

    fun TextChanged()
    {
        binding!!.inputEmailtext.addTextChangedListener(object :TextWatcher{
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                if (binding!!.inputEmailtext.text.isNotEmpty())
                {
                    binding!!.buttonDalee.background = this@Input_and_register.getDrawable(R.drawable.shape_button2)
                }
                else
                {
                    binding!!.buttonDalee.background = this@Input_and_register.getDrawable(R.drawable.shape_button)
                }
            }
        })

    }
}