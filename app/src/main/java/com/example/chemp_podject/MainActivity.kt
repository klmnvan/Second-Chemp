package com.example.chemp_podject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chemp_podject.Input_and_register
import com.example.chemp_podject.databinding.ActivityMainBinding
import com.example.chemp_podject.on_board1
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val des = "my_settings"
    object goActicity{
        var bool: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var thread: Thread = object : Thread(){
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(2)
                    startActivity(Intent(this@MainActivity, on_board1::class.java))
                }
                catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }
}
