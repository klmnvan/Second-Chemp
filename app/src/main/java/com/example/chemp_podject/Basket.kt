package com.example.chemp_podject

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.ModelBasket
import com.example.chemp_podject.databinding.ActivityBasketBinding

class Basket : AppCompatActivity(), AdapterOrder.Order {
    lateinit var binding: ActivityBasketBinding
    lateinit var listOrder: List<BlockModel>
    var listOrderBasket: List<ModelBasket> = ArrayList<ModelBasket>()
    var adapterOrder = AdapterOrder(this)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putPerson()
       /* listOrder = intent.getSerializableExtra("order") as List<BlockModel>
        var a = 5*/
    }

    /* fun CountOrders(){
        bindingRVBasket.buttonMinus.setOnClickListener(){
            if(countOrders >= 1){
                countOrders += 1
            }
            putPerson()
        }
    }*/
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
        var a =5
    }
fun calculation()
{
    var summa = 0
    for (i in listOrderBasket)
    {
        summa+=i.count*i.blockModel.price.toInt()
    }
    binding.textPrice.text = "$summa ₽"
}
    override fun CountOrder(blockModel: BlockModel) {
        delete(blockModel)
    }
}
