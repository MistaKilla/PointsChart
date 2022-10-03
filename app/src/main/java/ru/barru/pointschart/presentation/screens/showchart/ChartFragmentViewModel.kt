package ru.barru.pointschart.presentation.screens.showchart

import ru.barru.pointschart.data.models.MyPoint
import ru.barru.pointschart.presentation.utils.sortedForChart
import ru.barru.pointschart.presentation.utils.toFloatArray

class ChartFragmentViewModel(val points: ArrayList<MyPoint>) {
    val tableHeader = TableItemViewModel("X", "Y")
    val chartData = points.sortedForChart().toFloatArray()
}