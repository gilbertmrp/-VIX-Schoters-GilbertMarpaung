package com.example.aplikasinewssederhana

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val newsList: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView : ImageView = itemView.findViewById(R.id.img_news)
        val tvName : TextView = itemView.findViewById(R.id.tv_name)
        val tvTitle : TextView = itemView.findViewById(R.id.tv_title)

        init {
            itemView.setOnClickListener{
                val article = newsList[adapterPosition]
                val intent = Intent(itemView.context,DetailActivity::class.java)
                intent.putExtra("urlToImage", article.urlToImage)
                intent.putExtra("publishedAt", article.author)
                intent.putExtra("title", article.title)
                intent.putExtra("description", article.description)
                intent.putExtra("url", article.url)
                Log.d("index", newsList.indexOf(article).toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_news, parent,false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = newsList[position]
        holder.tvName.text = news.author
        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .into(holder.imgView)
        holder.tvTitle.text = news.title
    }

    override fun getItemCount(): Int = newsList.size

    fun setData(data: List<Article>) {
        this.newsList.clear()
        this.newsList.addAll(data)
        notifyDataSetChanged()
    }

}