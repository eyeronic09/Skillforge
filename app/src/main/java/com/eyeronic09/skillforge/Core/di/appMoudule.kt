package com.eyeronic09.skillforge.Core.di

import android.app.Application
import com.eyeronic09.skillforge.HomeScreen.data.remote.ReposistoryImpl.CourseReposistoryImpl
import com.eyeronic09.skillforge.HomeScreen.data.remote.api.CourseApi
import com.eyeronic09.skillforge.HomeScreen.data.remote.api.CourseApiImpl
import com.eyeronic09.skillforge.HomeScreen.domain.Reposistory.CourseRepository
import com.eyeronic09.skillforge.HomeScreen.ui.HomeScreenVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }

    }
    val appModule = module {
        single { NetworkClient.httpClient }
        single <CourseApi>{ CourseApiImpl(get()) }
        single <CourseRepository>{ CourseReposistoryImpl(get()) }
        viewModelOf(::HomeScreenVM)
    }
}