package com.example.yosimizrachi.culture.main

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.view.View
import com.example.yosimizrachi.culture.R
import com.example.yosimizrachi.culture.articles.ArticlesActivity
import javax.inject.Inject

class MainViewModel @Inject constructor(val context: Context) {

    val btnText = MutableLiveData<String>().apply { value = context.getString(R.string.go_to_articles) }

    fun onGoToClicked(v: View) {
        ArticlesActivity.newInstance(v.context)
    }

}