package com.example.chemp_podject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chemp_podject.databinding.ActivityBasketBinding

class Basket : AppCompatActivity() {
    lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}