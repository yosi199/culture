package com.example.yosimizrachi.culture.application

import android.content.Context
import com.example.yosimizrachi.culture.R
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun context(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun okHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun objectMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient, objectMapper: ObjectMapper, context: Context): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(context.getString(R.string.base_url))
                .client(client)
                .build()
    }
}