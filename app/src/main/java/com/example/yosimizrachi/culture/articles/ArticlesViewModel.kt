package com.example.yosimizrachi.culture.articles

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.yosimizrachi.culture.endpoints.ArticlesEndpoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val adapter: ArticlesAdapter, retrofit: Retrofit) {

    val liveAdapter = MutableLiveData<ArticlesAdapter>()

    companion object {
        val TAG: String = ArticlesViewModel::class.java.name
    }

    init {
        val endpoint = retrofit.create(ArticlesEndpoint::class.java)
        endpoint.articles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.setData(it.data)
                    liveAdapter.value = adapter
                }, {
                    Log.d(TAG, "OnError ${it.localizedMessage}")
                })
    }
}