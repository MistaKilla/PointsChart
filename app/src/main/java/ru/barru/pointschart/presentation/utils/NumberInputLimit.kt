package ru.barru.pointschart.presentation.utils

/**
 * Some number input limits, with default values taken from api doc
 */
data class NumberInputLimit(val min: Int, val max: Int) {
    companion object {
        fun default() = NumberInputLimit(1, 1000)
    }
}