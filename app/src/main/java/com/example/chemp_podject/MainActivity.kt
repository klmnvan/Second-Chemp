package com.example.chemp_podject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chemp_podject.databinding.ActivityMainBinding
import com.example.chemp_podject.databinding.ActivityOnBoard1Binding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    TimeUnit.SECONDS.sleep(2)
                    val intent = Intent(this@MainActivity,on_board1::class.java)
                    startActivity(intent)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
        val decorView: View = window.decorView
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}