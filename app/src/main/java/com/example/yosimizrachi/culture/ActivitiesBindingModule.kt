package com.example.yosimizrachi.culture

import com.example.yosimizrachi.culture.articles.ArticlesActivity
import com.example.yosimizrachi.culture.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector(modules = [MainActivity.Module::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ArticlesActivity.Module::class])
    abstract fun articlesActivity(): ArticlesActivity

}
