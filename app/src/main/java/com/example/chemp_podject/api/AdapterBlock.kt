package com.example.chemp_podject.api

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.Home
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ActivityHomeBinding
import com.example.chemp_podject.databinding.ItemBlockBinding

class AdapterBlock(private val listener: Home): RecyclerView.Adapter<AdapterBlock.BlockHolder>() {
    val blockModelList = ArrayList<BlockModel>()

    class BlockHolder(item: View) : RecyclerView.ViewHolder(item){
        var bindingBlock = ItemBlockBinding.bind(item)
        var bool: Boolean = true
        @SuppressLint("ResourceAsColor")
        fun bind(block: BlockModel, listener: Listener)
        {
            /*if(Home.Home.listIndexOrder.contains(position)){
                bindingBlock.ButtonInBlock.background = bindingBlock.root.context.getDrawable(R.drawable.button_blue_stroke_style)
                bindingBlock.ButtonInBlock.setTextColor(Color.BLUE)
                bindingBlock.ButtonInBlock.setText("Убрать")
                listener.Order(block)
                bool = false
            }*/
            bindingBlock.TextNameBlock.text = block.name
            bindingBlock.TextTimeResult.text = block.time_result
            bindingBlock.TextPrice.text = block.price + " ₽"
            bindingBlock.listBlock.setOnClickListener(){
                listener.Click(block)
            }
            bindingBlock.ButtonInBlock.setOnClickListener(){
                //bindingHome!!.rowGoButton.visibility = View.VISIBLE
                if(bool)
                {
                    bindingBlock.ButtonInBlock.background = bindingBlock.root.context.getDrawable(R.drawable.button_blue_stroke_style)
                    bindingBlock.ButtonInBlock.setTextColor(Color.BLUE)
                    bindingBlock.ButtonInBlock.setText("Убрать")
                    listener.Order(block)
                    bool = false
                    //listener.OrderPrice()
                }
                else{
                    bindingBlock.ButtonInBlock.background = bindingBlock.root.context.getDrawable(R.drawable.button_home_blue_style)
                    bindingBlock.ButtonInBlock.setTextColor(Color.WHITE)
                    bindingBlock.ButtonInBlock.setText("Добавить")
                    listener.deleteOrder(block)
                    bool = true
                    //listener.OrderPrice()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_block, parent, false)
        return BlockHolder(view)
    }

    override fun getItemCount(): Int {
        return blockModelList.size
    }

    override fun onBindViewHolder(holder: BlockHolder, position: Int) {
        if(Person.listOrder.contains(blockModelList[position])){
            holder.bindingBlock.ButtonInBlock.background = holder.bindingBlock.root.context.getDrawable(R.drawable.button_blue_stroke_style)
            holder.bindingBlock.ButtonInBlock.setTextColor(Color.BLUE)
            holder.bindingBlock.ButtonInBlock.setText("Убрать")
            holder.bool = false
        }
        /*if(Home.Home.listIndexOrder.contains(position)){
        }*/
        holder.bind(blockModelList[position], listener)
    }
    fun addBlock(block: BlockModel)
    {
        blockModelList.add(block)
        notifyDataSetChanged()
    }
    interface Listener{
        fun Click(block: BlockModel)
        fun Order(block: BlockModel)
        fun deleteOrder(block: BlockModel)
    }

}