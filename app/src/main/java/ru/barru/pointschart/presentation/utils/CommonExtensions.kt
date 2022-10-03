package ru.barru.pointschart.presentation.utils

import androidx.databinding.ObservableInt
import ru.barru.pointschart.data.models.MyPoint

/**
 * Allows trigger update via BindingAdapter even with same value
 */
fun ObservableInt.forceSet(value: Int) {
    set(value)
    notifyChange()
}

/**
 * Converts list of points to FloatArray like [x0, y0, x1, y1]
 */
fun List<MyPoint>.toFloatArray() = ArrayList<Float>().also {
    for (i in 0 until size - 1) {
        it.add(this[i].x)
        it.add(this[i].y)
        it.add(this[i + 1].x)
        it.add(this[i + 1].y)
    }
}.toFloatArray()

/**
 * Сортировка массива наших точек по возрастанию x
 * нужно для построения графиков
 */
fun List<MyPoint>.sortedForChart() = this.sortedBy { it.x }