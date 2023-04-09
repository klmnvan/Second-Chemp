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
    val sp = getSharedPreferences(des, Context.MODE_PRIVATE)
    lateinit var e: SharedPreferences.Editor
    object goActicity{
        var bool: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val hasVisited = sp.getInt("hasVisited", 0)
        var thread: Thread = object : Thread(){
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(2)
                    //Сработает только в сессии2
                    if(hasVisited == 0){
                        e = sp.edit()
                        e.putInt("hasVisited", 1)
                        e.commit() // не забудьте подтвердить изменения
                        startActivity(Intent(this@MainActivity, on_board1::class.java))
                    }
                    if (hasVisited == 1){
                        startActivity(Intent(this@MainActivity, Input_and_register::class.java))
                    }

                    if (hasVisited == 2){
                        startActivity(Intent(this@MainActivity, CreatePassword::class.java))
                    }
                    if (hasVisited == 3){
                        startActivity(Intent(this@MainActivity, Home::class.java))
                    }
                }
                catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }
}
