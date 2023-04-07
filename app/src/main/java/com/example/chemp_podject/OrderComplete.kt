package com.example.chemp_podject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chemp_podject.databinding.ActivityOrderCompleteBinding

class OrderComplete : AppCompatActivity() {
    private lateinit var binding: ActivityOrderCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderCompleteBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

    }
}