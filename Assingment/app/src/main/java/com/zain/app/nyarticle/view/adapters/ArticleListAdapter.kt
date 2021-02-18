package com.zain.app.nyarticle.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zain.app.nyarticle.R
import com.zain.app.nyarticle.databinding.ItemArticleBinding
import com.zain.app.nyarticle.model.articleDataHolder.Articles
import com.zain.app.nyarticle.view.viewModel.ArticleViewModel


class ArticleListAdapter() :
    RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {
    private lateinit var articleList: MutableList<Articles>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return if (::articleList.isInitialized) {
            articleList.size
        } else {
            0
        }
    }

    fun updateArticleList(list: List<Articles>) {
        this.articleList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun addArticles(list: List<Articles>) {
        if (!::articleList.isInitialized)
            this.articleList = list.toMutableList()
        this.articleList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ArticleViewModel()

        fun bind(article: Articles) {
            viewModel.bind(article)
            binding.viewModel = viewModel
        }
    }
}