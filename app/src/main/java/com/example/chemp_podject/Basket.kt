package com.example.chemp_podject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.ModelBasket
import com.example.chemp_podject.databinding.ActivityBasketBinding
import com.example.chemp_podject.databinding.ItemBasketBinding

class Basket : AppCompatActivity(), AdapterOrder.Order {
    lateinit var binding: ActivityBasketBinding
    lateinit var listOrder: List<BlockModel>
    var listOrderBasket: List<ModelBasket>  = ArrayList<ModelBasket>()
    var adapterOrder = AdapterOrder(this)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putPerson()
    }

   /* fun CountOrders(){
        bindingRVBasket.buttonMinus.setOnClickListener(){
            if(countOrders >= 1){
                countOrders += 1
            }
            putPerson()
        }
    }*/
    fun putPerson ()
    {
        listOrder =  Home.Home.listOrder
        for(i in Home.Home.listOrder)
        {
            listOrderBasket += ModelBasket(i,1)
        }


        binding.listBascket.layoutManager = GridLayoutManager(this@Basket, 1)
        binding.listBascket.adapter = adapterOrder
        //var block = intent.getSerializableExtra("order")
        //val AllPrice = listOrder.map { it.price }.toSet().toList()
     /*   binding.textPrice.setText("${countOrder * (listOrder.map { it.price.toInt()}.sum()) } ₽")*/
        //listOrder = listOrder.map { it.price }.toSet().toList()
        //val listOrder: List<BlockModel> = listOrder
        if (listOrder.isNotEmpty()) {
            for (element in listOrder) {
                adapterOrder.addOrder(element)
            }
        }
    }

    override fun CountOrder(blockModel: BlockModel) {
     /*   listOrderBasket.contains(element = blockModel).*/
    }


}