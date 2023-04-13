package com.example.chemp_podject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.api.BlockModel
import com.example.chemp_podject.databinding.ItemOrderPacientBinding

class AdapterSelectOrder: RecyclerView.Adapter<AdapterSelectOrder.OrderHolder>() {
    var listOrder = ArrayList<BlockModel>()

    class OrderHolder(item: View): RecyclerView.ViewHolder(item){
        var binding = ItemOrderPacientBinding.bind(item)

        fun bind(order: BlockModel){
            binding.textNamePrivivki.setText(order.name)
            binding.textPrice.setText(order.price + " ₽")
            binding.checkbox.isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSelectOrder.OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_pacient, parent, false)
        return OrderHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterSelectOrder.OrderHolder, position: Int) {
        holder.bind(listOrder[position])
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

    fun addOrders(order: BlockModel){
        listOrder.add(order)
        notifyDataSetChanged()
    }
}