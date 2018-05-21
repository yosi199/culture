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

/**
 *  This is a bit of an overkill - the task is simple yet I wanted to demonstrate proficiency in all relevant
 *  technologies and libraries we've discussed in the interview.
 *
 *  In this project you can find use of Dependency Injection using Dagger2, networking using RxJava and Retrofit,
 *  ViewModel and LiveData and of course - Kotlin
 */
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
