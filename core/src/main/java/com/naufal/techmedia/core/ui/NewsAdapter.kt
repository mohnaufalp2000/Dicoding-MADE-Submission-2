package com.naufal.techmedia.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufal.techmedia.core.R
import com.naufal.techmedia.core.databinding.ListNewsBinding
import com.naufal.techmedia.core.domain.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setNews(list: List<News>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            with(binding) {
                txtTitleNews.text = news.title
                txtDate.text = news.publishedAt?.subSequence(0, 9)

                if (news.urlToImage.equals("null")){
                    Glide.with(itemView.context)
                        .load(R.drawable.placeholder)
                        .into(imgNews)
                } else {
                    Glide.with(itemView.context)
                        .load(news.urlToImage)
                        .into(imgNews)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}