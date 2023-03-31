package com.example.chemp_podject.api

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.Home
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ItemBlockBinding

class AdapterBlock(private val listener: Home): RecyclerView.Adapter<AdapterBlock.BlockHolder>() {
    val blockModelList = ArrayList<BlockModel>()

    class BlockHolder(item: View) : RecyclerView.ViewHolder(item){
        private var bindingBlock = ItemBlockBinding.bind(item)
        @SuppressLint("ResourceAsColor")
        fun bind(block: BlockModel, listener: Listener)
        {
            bindingBlock.TextNameBlock.text = block.name
            bindingBlock.TextTimeResult.text = block.time_result
            bindingBlock.TextPrice.text = block.price + " ₽"
            bindingBlock.listBlock.setOnClickListener(){
                listener.Click(block)
            }
            bindingBlock.ButtonInBlock.setOnClickListener(){
                //bindingBlock.ButtonInBlock.isClickable = false
                bindingBlock.ButtonInBlock.background = bindingBlock.root.context.getDrawable(R.drawable.button_home_white_style)
                bindingBlock.ButtonInBlock.setTextColor(R.color.blue_button)
                listener.Order(block)
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
    }

}