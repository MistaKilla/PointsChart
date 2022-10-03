package ru.barru.pointschart.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import ru.barru.pointschart.presentation.custom.ChartView

@BindingAdapter("snackBarText")
fun showSnackBar(view: View, stringResId: Int) {
    if (stringResId > 0) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_SHORT).show()
    }
}

@BindingAdapter("chartData")
fun setChartData(view: ChartView, data: FloatArray) = view.setData(data)