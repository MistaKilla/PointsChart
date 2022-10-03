package ru.barru.pointschart.presentation.screens.showchart

import ru.barru.pointschart.data.models.MyPoint

class TableItemViewModel(val firstText: String, val secondText: String) {
    constructor(point: MyPoint) : this(point.x.toString(), point.y.toString())
}