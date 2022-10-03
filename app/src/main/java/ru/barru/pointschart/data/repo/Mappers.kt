package ru.barru.pointschart.data.repo

import ru.barru.pointschart.data.api.ServerPoint
import ru.barru.pointschart.data.models.MyPoint

internal fun ServerPoint.toMyPoint() = MyPoint(x, y)
