package com.example.yosimizrachi.culture.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.yosimizrachi.culture.R
import com.example.yosimizrachi.culture.application.App
import com.example.yosimizrachi.culture.databinding.ActivityMainBinding
import dagger.Provides

import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.model = viewModel

        viewModel.btnText.observe(this, Observer<String> { binding.articlesBtn.text = it })

    }

    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @JvmStatic
            @Provides
            fun viewModel(app: App) = MainViewModel(app)
        }
    }

    @Subcomponent(modules = [Module::class])
    interface Component : AndroidInjector<MainActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<MainActivity>()

    }
}
