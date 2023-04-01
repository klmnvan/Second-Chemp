package com.example.chemp_podject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.api.ModelBasket
import com.example.chemp_podject.databinding.ItemBasketBinding

class AdapterOrder (private val listener: Basket): RecyclerView.Adapter<AdapterOrder.OrderHolder>() {
    val orderList = ArrayList<ModelBasket>()

    class OrderHolder(item: View): RecyclerView.ViewHolder(item){
        private var bindingBasket = ItemBasketBinding.bind(item)

        fun bind(modelBasket: ModelBasket, listener: Basket){
            bindingBasket.textName.text = modelBasket.blockModel.name
            bindingBasket.textPrice.text = modelBasket.blockModel.price + " ₽"
            bindingBasket.buttonPlus.setOnClickListener(){
                modelBasket.count++
                bindingBasket.textPacient.text = modelBasket.count.toString() + " пациент(а)"
                listener.CountOrder(modelBasket.blockModel)
            }
            bindingBasket.buttonMinus.setOnClickListener(){
                if(modelBasket.count > 1){
                    modelBasket.count--
                    bindingBasket.textPacient.text = modelBasket.count.toString() + " пациент(а)"
                    listener.CountOrder(modelBasket.blockModel)
                }
            }
            bindingBasket.buttonClose.setOnClickListener(){
                listener.deleteOrder(modelBasket)
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

    fun addOrder(modelBasket: ModelBasket)
    {
        orderList.add(modelBasket)
        notifyDataSetChanged()
    }

    interface Order{

        fun CountOrder(blockModel: BlockModel){}
        fun deleteOrder(blockModel: ModelBasket)
    }
}