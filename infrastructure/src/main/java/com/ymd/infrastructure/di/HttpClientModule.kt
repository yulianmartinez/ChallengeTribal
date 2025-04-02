package com.ymd.infrastructure.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpClientModule {
    private val requestTimeout = 10
    private val baseUrl = "https://api.chucknorris.io/jokes/"

    @Singleton
    @Provides
    fun provideGsonCreate(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().connectTimeout(requestTimeout.toLong(), TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder.cache(null).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

}