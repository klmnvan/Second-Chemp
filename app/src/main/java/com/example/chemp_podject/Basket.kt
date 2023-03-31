package com.example.chemp_podject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.api.AdapterOrder
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.NewsModel
import com.example.chemp_podject.databinding.ActivityBasketBinding
import com.example.chemp_podject.models.PolzovatModel

class Basket : AppCompatActivity() {
    lateinit var binding: ActivityBasketBinding
    lateinit var listOrder: List<BlockModel>
    var adapterOrder = AdapterOrder()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putPerson ()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun putPerson ()
    {
        listOrder =  Home.Home.listOrder
        binding.listBascket.layoutManager = GridLayoutManager(this@Basket, 1)
        binding.listBascket.adapter = adapterOrder
        //var block = intent.getSerializableExtra("order")
        //val AllPrice = listOrder.map { it.price }.toSet().toList()
        binding.textPrice.setText("${listOrder.map { it.price.toInt()}.sum() } ₽")
        //listOrder = listOrder.map { it.price }.toSet().toList()
        //val listOrder: List<BlockModel> = listOrder
        if (listOrder.isNotEmpty()) {
            for (element in listOrder) {
                adapterOrder.addOrder(element)
            }
        }
    }
}