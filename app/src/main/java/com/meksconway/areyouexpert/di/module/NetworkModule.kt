package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.BuildConfig
import com.meksconway.areyouexpert.data.service.remote.TriviaApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BuildConfig.TRIVIA_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideTriviaApiService(retrofit: Retrofit): TriviaApiService {
        return retrofit.create()
    }


}