package ru.barru.pointschart.data.api

import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.barru.pointschart.data.repo.IApi

class Api : IApi {
    private val mRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://hr-challenge.interactivestandard.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun requestPoints(count: Int): Single<ServerPoints> =
        mRetrofit.create(IPointsRequests::class.java).get(count)
}