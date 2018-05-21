package com.example.yosimizrachi.culture.articles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.yosimizrachi.culture.R
import com.example.yosimizrachi.culture.articles.ArticlesAdapter.ArticleHolder
import com.example.yosimizrachi.culture.responses.ArticleResponse
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class ArticlesAdapter @Inject constructor(@Named("from") private val formatter: SimpleDateFormat, @Named("to") private val toFormat: SimpleDateFormat) : RecyclerView.Adapter<ArticleHolder>() {

    private var articles: List<ArticleResponse.Article> = listOf()

    fun setData(articles: List<ArticleResponse.Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleHolder(view)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles[position]

        holder.articleCategory.text = article.category
        holder.articleTitle.text = article.title
        holder.likedCount.text = article.likesCount.toString()
        holder.writer.text = article.author.authorName
        val parsedDate = formatter.parse(article.metaData.creationTime)
        holder.date.text = toFormat.format(parsedDate)

        Glide.with(holder.articleImage)
                .load(article.imageUrl)
                .into(holder.articleImage)

        Glide.with(holder.authorImage)
                .load(article.author.authorAvatar.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.authorImage)
    }

    class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleImage: ImageView = itemView.findViewById(R.id.article_image)
        val authorImage: ImageView = itemView.findViewById(R.id.author_image)
        val articleTitle: TextView = itemView.findViewById(R.id.articles_title)
        val articleCategory: TextView = itemView.findViewById(R.id.article_category)
        val writer: TextView = itemView.findViewById(R.id.writer)
        val likedCount: TextView = itemView.findViewById(R.id.likes_count)
        val date: TextView = itemView.findViewById(R.id.date)
    }
}