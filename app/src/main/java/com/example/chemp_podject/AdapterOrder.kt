package com.example.chemp_podject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.databinding.ItemBasketBinding

class AdapterOrder (private val listener: Basket): RecyclerView.Adapter<AdapterOrder.OrderHolder>() {
    val orderList = ArrayList<BlockModel>()
    var countOrder: Int = 0

    class OrderHolder(item: View): RecyclerView.ViewHolder(item){
        private var bindingBasket = ItemBasketBinding.bind(item)

        fun bind(order: BlockModel, listener: Basket){
            bindingBasket.textName.text = order.name
            bindingBasket.textPrice.text = order.price
            bindingBasket.buttonPlus.setOnClickListener(){
               listener
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return OrderHolder(view)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(orderList[position], listener)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    fun addOrder(order: BlockModel)
    {
        orderList.add(order)
        notifyDataSetChanged()
    }

    interface Order{
        fun CountOrder(blockModel: BlockModel)
    }
}