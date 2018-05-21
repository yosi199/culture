package com.example.yosimizrachi.culture.articles

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.yosimizrachi.culture.R
import com.example.yosimizrachi.culture.application.App
import com.example.yosimizrachi.culture.databinding.ArticlesActivityBinding
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class ArticlesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ArticlesViewModel

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ArticlesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ArticlesActivityBinding>(this, R.layout.articles_activity)
        binding.model = viewModel

        viewModel.liveAdapter.observe(this, Observer<ArticlesAdapter> {
            binding.articlesList.layoutManager = LinearLayoutManager(this)
            binding.articlesList.adapter = it
        })
    }

    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @Provides
            @JvmStatic
            @Named("from")
            fun fromFormatter() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

            @Provides
            @JvmStatic
            @Named("to")
            fun toFormatter() = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())

            @Provides
            fun listAdapter(fromFormat: SimpleDateFormat, toFormat: SimpleDateFormat) = ArticlesAdapter(fromFormat, toFormat)

            @JvmStatic
            @Provides
            fun viewModel(app: App, adapter: ArticlesAdapter, retrofit: Retrofit) = ArticlesViewModel(adapter, retrofit)
        }
    }

    @Subcomponent(modules = [Module::class])
    interface Component : AndroidInjector<ArticlesActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<ArticlesActivity>()
    }
}