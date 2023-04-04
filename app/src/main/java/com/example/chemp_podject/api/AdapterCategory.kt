package com.example.chemp_podject.api

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chemp_podject.Home
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ItemButtonBinding

class AdapterCategory(private val listener: Home) : RecyclerView.Adapter<AdapterCategory.CategoryHolder>() {
    private val categoryModelList = ArrayList<String>()

    class CategoryHolder(item: View) :RecyclerView.ViewHolder(item){
        var binding = ItemButtonBinding.bind(item)

        fun bind(category: String, listener: Listener){
            binding.ButtonCatalog.text = category
            //absoluteAdapterPosition - передает позицию элемента в Home, в метод, который мы сами создаем
            binding.ButtonCatalog.setOnClickListener(){
                listener.Click(category, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent,false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryModelList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        /*if(Home.Home.mainPositionCategory == position){
            holder.binding.ButtonCatalog.setBackgroundResource(R.drawable.button_home_blue_style)
            holder.binding.ButtonCatalog.setTextColor(Color.WHITE)
        }*/
        holder.bind(categoryModelList[position], listener)
    }

    fun addCategogory(category: String)
    {
        categoryModelList.add(category)
        notifyDataSetChanged()
    }

    interface Listener{
        fun Click(category: String, absoluteAdapterPosition: Int)
    }
}