package com.ducnguyen2102.baseclean.data.remote.builder

import android.content.Context
import com.ducnguyen2102.baseclean.data.BuildConfig
import com.ducnguyen2102.baseclean.data.Config
import com.ducnguyen2102.baseclean.data.HttpClient
import com.ducnguyen2102.baseclean.data.remote.auth.OauthRefreshAuthenticator
import com.ducnguyen2102.baseclean.data.remote.factory.NetworkResponseAdapterFactory
import com.ducnguyen2102.baseclean.data.remote.interceptor.HeaderInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitBuilder @Inject constructor(
    @ApplicationContext private val context: Context,
    private val oauthRefreshAuthenticator: OauthRefreshAuthenticator,
    private val headerInterceptor: HeaderInterceptor,
    private val callAdapter: NetworkResponseAdapterFactory
) {
    fun build(): Retrofit {
        return OkHttpClient.Builder().apply {
            connectTimeout(HttpClient.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpClient.WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpClient.READ_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            authenticator(oauthRefreshAuthenticator)
            addInterceptor(headerInterceptor)
        }.let { client ->
            Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(client.build())
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}