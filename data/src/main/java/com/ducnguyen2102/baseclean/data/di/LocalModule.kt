package com.ducnguyen2102.baseclean.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ducnguyen2102.baseclean.data.Config
import com.ducnguyen2102.baseclean.data.local.prefs.AppPrefs
import com.ducnguyen2102.baseclean.data.local.prefs.AppPrefsImpl
import com.ducnguyen2102.baseclean.data.local.room.AppDatabase
import com.ducnguyen2102.baseclean.data.local.room.dao.ColorDao
import com.ducnguyen2102.baseclean.data.local.room.dao.ProductDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideAppPrefs(appPrefsImpl: AppPrefsImpl): AppPrefs {
        return appPrefsImpl
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, Config.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao = appDatabase.productDao()

    @Provides
    @Singleton
    fun provideColorDao(appDatabase: AppDatabase): ColorDao = appDatabase.colorDao()
}