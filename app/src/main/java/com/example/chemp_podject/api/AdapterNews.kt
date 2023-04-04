package com.example.chemp_podject.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chemp_podject.R
import com.example.chemp_podject.databinding.ItemNewBinding

class AdapterNews : RecyclerView.Adapter<AdapterNews.NewsHolder>() {
    private val newsModelList = ArrayList<NewsModel>()

    class NewsHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemNewBinding.bind(item)
        fun bind(news: NewsModel) = with(binding)
        {
            binding.textCheckUp.text = news.name
            binding.textIssled.text = news.description
            binding.textSumma.text = news.price + " ₽"
            Glide.with(binding.root).load(news.image).into(binding.pictureOneBlock)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new, parent, false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return newsModelList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(newsModelList[position])
    }
    fun addNews(news: NewsModel)
    {
        newsModelList.add(news)
        notifyDataSetChanged()
    }
}