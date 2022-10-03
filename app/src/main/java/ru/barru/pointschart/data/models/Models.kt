package ru.barru.pointschart.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// да, знаю про штатный класс Point, но для абстракции используем такое
@Parcelize
data class MyPoint(val x: Float, val y: Float) : Parcelable