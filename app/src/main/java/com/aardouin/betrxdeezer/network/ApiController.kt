package com.aardouin.betrxdeezer.network

import com.aardouin.betrxdeezer.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by WOPATA on 11/10/2017.
 */


object ApiController {
    private val DATE_FORMAT = "yyyy-MM-dd' 'HH:mm:ss" // 1990-09-17 23:59:59

    private var restApiController: Retrofit

    val playlistApi: PlaylistAPI
        get() = restApiController.create(PlaylistAPI::class.java)

    init {
        val gson = GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(loggerInterceptor).build()

        restApiController = Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }


}