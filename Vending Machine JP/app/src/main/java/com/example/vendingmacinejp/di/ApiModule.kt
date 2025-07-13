package com.example.vendingmacinejp.di

import com.example.ordermonkey.retrofit.home.ApiInterface
import com.example.ordermonkey.retrofit.terminal.ApiTerminal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideService1Api(@Named("Service1") retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideService2Api(@Named("Service2") retrofit: Retrofit): ApiTerminal {
        return retrofit.create(ApiTerminal::class.java)
    }
}