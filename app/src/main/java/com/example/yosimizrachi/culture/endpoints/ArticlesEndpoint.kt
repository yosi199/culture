package com.example.yosimizrachi.culture.endpoints

import com.example.yosimizrachi.culture.responses.ArticleResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ArticlesEndpoint {

    @GET("home-assignment/response.json")
    fun articles(): Single<ArticleResponse>
}