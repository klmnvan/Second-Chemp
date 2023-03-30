package com.example.chemp_podject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chemp_podject.api.AdapterOrder
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.NewsModel
import com.example.chemp_podject.databinding.ActivityBasketBinding
import com.example.chemp_podject.models.PolzovatModel

class Basket : AppCompatActivity() {
    lateinit var binding: ActivityBasketBinding
    lateinit var listOrder: List<BlockModel>
    var adapterOrder = AdapterOrder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        putPerson ()
    }

    fun putPerson ()
    {
        listOrder = intent.getSerializableExtra("order") as List<BlockModel>
        val listCharacter: List<BlockModel> = listOrder
        if (listCharacter.isNotEmpty()) {
            for (element in listCharacter) {
                adapterOrder.addOrder(element)
            }
        }
    }
}