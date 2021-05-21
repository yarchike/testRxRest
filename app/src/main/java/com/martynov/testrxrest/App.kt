package com.martynov.testrxrest

import android.app.Application
import com.google.gson.GsonBuilder
import com.martynov.testrxrest.api.API
import com.martynov.testrxrest.repository.NetworkRepository
import com.martynov.testrxrest.repository.Repository
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class App : Application() {
    companion object {
        lateinit var repository: Repository
            private set
    }

    override fun onCreate() {
        super.onCreate()

        val rxAdapter = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())
        val httpLoggerInterceptor = HttpLoggingInterceptor()
        httpLoggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggerInterceptor)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://93.179.85.126:5050/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxAdapter)
            .build()
        val api = retrofit.create(API::class.java)
        repository = NetworkRepository(api)

    }
}