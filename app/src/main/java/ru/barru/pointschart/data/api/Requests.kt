package ru.barru.pointschart.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface IPointsRequests {
    @GET("api/test/points")
    fun get(@Query("count") count: Int): Single<ServerPoints>
}
