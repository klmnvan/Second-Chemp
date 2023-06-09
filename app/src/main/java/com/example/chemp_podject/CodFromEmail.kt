package com.example.chemp_podject

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.chemp_podject.api.ApiRequest
import com.example.chemp_podject.api.ApiRequestBlock
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.databinding.ActivityCodFromEmailBinding
import com.example.chemp_podject.databinding.ActivityCreatePasswordBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.internal.notify
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class CodFromEmail: AppCompatActivity() {
    private var binding: ActivityCodFromEmailBinding? = null
    var code: String = "0000"
    lateinit var email: String
    var boolean: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodFromEmailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        TextChanged()
        Timer()
        var arguments = intent.extras
        email = arguments?.getString("email").toString()
        binding!!.strelka.setOnClickListener(){
            startActivity(Intent(this@CodFromEmail, Input_and_register::class.java))
        }
    }

    fun Timer(){
        val timer = object : CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val elapsedSeconds = millisUntilFinished / 1000
                binding!!.otchetTime.text = "Отправить код повторно можно \nбудет через ${elapsedSeconds} секунд"
            }
            override fun onFinish() {
            }
        }
        timer.start()
    }
    fun TextChanged()
    {
        binding!!.inputNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
               if(binding!!.inputNumber1.length()==1)
               {
                   binding!!.inputNumber2.requestFocus()               }
            }
        })
        binding!!.inputNumber2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.length==1) {
                    binding!!.inputNumber3.requestFocus()
                }
            }
        })
        binding!!.inputNumber3.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(binding!!.inputNumber3.length()==1) {
                    binding!!.inputNumber4.requestFocus()
                    binding!!.inputNumber4.text = null
                }
            }
        })
        binding!!.inputNumber4.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.length==1) {
                    init()
                }
            }
        })
    }
    fun init()
    {
        if(binding!!.inputNumber1.text.isNotEmpty() && binding!!.inputNumber2.text.isNotEmpty() && binding!!.inputNumber3.text.isNotEmpty() && binding!!.inputNumber4.text.isNotEmpty()){
            code = binding!!.inputNumber1.text.toString() + binding!!.inputNumber2.text.toString() + binding!!.inputNumber3.text.toString() + binding!!.inputNumber4.text.toString()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://medic.madskill.ru")
                    .client(httpClient)
                    .build()
            val requestApi = retrofit.create(ApiRequestBlock::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    requestApi.postCode(code,email).awaitResponse()
                    Log.d("Response", "success")
                    runOnUiThread {   Toast.makeText(this@CodFromEmail,"Error",Toast.LENGTH_LONG).show()}
                } catch (e: Exception) {
                    boolean = 1
                    Log.d(ContentValues.TAG, e.toString())
                    startActivity(Intent(this@CodFromEmail, CreatePassword::class.java))
                    finish()
                }
            }
            if(boolean!=1){
                binding!!.inputNumber1.text = null
                binding!!.inputNumber2.text = null
                binding!!.inputNumber3.text = null
                binding!!.inputNumber4.text = null
                binding!!.inputNumber1.requestFocus()
            }
/*
            if(code == ""){
                Toast.makeText(applicationContext,"Неправильный код", Toast.LENGTH_SHORT).show()
            }*/
            /* startActivity(Intent(this@CodFromEmail, CreatePassword::class.java))*/
        }


    }
}