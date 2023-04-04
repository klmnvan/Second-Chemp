package com.example.chemp_podject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.ModelBasket
import com.example.chemp_podject.databinding.ActivityBasketBinding

class Basket : AppCompatActivity(), AdapterOrder.Order {
    lateinit var binding: ActivityBasketBinding
    lateinit var listOrder: List<BlockModel>
    var listOrderBasket: List<ModelBasket> = ArrayList()
    var adapterOrder = AdapterOrder(this)


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putPerson()
        init()
       /* listOrder = intent.getSerializableExtra("order") as List<BlockModel>
        var a = 5*/
    }
    fun init(){
        binding.buttonOrder.setOnClickListener(){
            startActivity(Intent(this@Basket, MakeOrder::class.java))
        }
        binding.buttonBack.setOnClickListener(){
            startActivity(Intent(this@Basket, Home::class.java))
            Home.Home.listOrder = emptyList()
            finish()
        }
        binding.buttonBasket.setOnClickListener(){
            binding.listBascket.visibility = View.GONE
            binding.textBasketNull.visibility = View.VISIBLE
        }
    }
    fun putPerson() {
        listOrder = Home.Home.listOrder
        for (i in Home.Home.listOrder) {
            listOrderBasket += ModelBasket(i, 1)
        }
        calculation()
        binding.listBascket.layoutManager = GridLayoutManager(this@Basket, 1)
        binding.listBascket.adapter = adapterOrder
        if (listOrderBasket.isNotEmpty()) {
            for (element in listOrderBasket) {
                adapterOrder.addOrder(element)
            }
        }
    }
    fun delete(blockModel: BlockModel)
    {
        listOrder = listOrder - blockModel
        var a = 5
        calculation()
    }

    fun DeleteOrder(basket: ModelBasket)
    {
        Home.Home.listOrder = Home.Home.listOrder - basket.blockModel
        listOrderBasket = listOrderBasket - basket
        binding.listBascket.layoutManager = GridLayoutManager(this@Basket, 1)
        binding.listBascket.adapter = adapterOrder
        adapterOrder.orderList.clear()
        if (listOrderBasket.isNotEmpty()) {
            for (element in listOrderBasket) {
                adapterOrder.addOrder(element)
            }
        }
        calculation()
    }
    fun calculation()
    {
        var summa = 0
        for (i in listOrderBasket)
        {
            summa+=i.count*i.blockModel.price.toInt()
        }
        binding.textPrice.text = "${summa} ₽"

    }
    override fun CountOrder(blockModel: BlockModel) {
        delete(blockModel)
    }

    override fun deleteOrder(blockModel: ModelBasket) {
        DeleteOrder(blockModel)
    }
}
