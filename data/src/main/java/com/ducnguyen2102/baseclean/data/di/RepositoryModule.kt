package com.ducnguyen2102.baseclean.data.di

import com.ducnguyen2102.baseclean.data.repository.ColorRepositoryImpl
import com.ducnguyen2102.baseclean.data.repository.ProductRepositoryImpl
import com.ducnguyen2102.baseclean.domain.repository.ColorRepository
import com.ducnguyen2102.baseclean.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository


    @Binds
    @Singleton
    abstract fun bindColorRepository(colorRepositoryImpl: ColorRepositoryImpl): ColorRepository
}