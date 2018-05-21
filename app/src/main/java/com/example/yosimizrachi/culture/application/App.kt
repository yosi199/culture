package com.example.yosimizrachi.culture.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent
                .builder()
                .create(this)
                .build()
    }
}