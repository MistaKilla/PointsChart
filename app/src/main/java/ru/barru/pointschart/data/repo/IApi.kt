package ru.barru.pointschart.data.repo

import io.reactivex.Single
import ru.barru.pointschart.data.api.ServerPoints

interface IApi {
    fun requestPoints(count: Int): Single<ServerPoints>
}