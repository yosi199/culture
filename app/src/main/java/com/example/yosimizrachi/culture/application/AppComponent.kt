package com.example.yosimizrachi.culture.application

import com.example.yosimizrachi.culture.ActivitiesBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivitiesBindingModule::class])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: App): Builder

        fun build(): AppComponent
    }

}