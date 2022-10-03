package ru.barru.pointschart.domain

import io.reactivex.Single
import ru.barru.pointschart.data.models.MyPoint

interface IRepo {
    fun getPoints(count: Int = 1): Single<ArrayList<MyPoint>>
}