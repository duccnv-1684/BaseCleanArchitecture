package com.ducnguyen2102.baseclean.data.di

import com.ducnguyen2102.baseclean.data.remote.api.ColorApi
import com.ducnguyen2102.baseclean.data.remote.api.ProductApi
import com.ducnguyen2102.baseclean.data.remote.builder.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit {
        return retrofitBuilder.build()
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideColorApi(retrofit: Retrofit): ColorApi = retrofit.create(ColorApi::class.java)
}