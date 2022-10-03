package ru.barru.pointschart.data.repo

import ru.barru.pointschart.data.models.MyPoint
import ru.barru.pointschart.domain.IRepo

class Repo(private val mApi: IApi) : IRepo {
    override fun getPoints(count: Int) = mApi.requestPoints(count)
        .map { arrayListOf<MyPoint>().apply { addAll(it.points.map { it.toMyPoint() }) } }

}