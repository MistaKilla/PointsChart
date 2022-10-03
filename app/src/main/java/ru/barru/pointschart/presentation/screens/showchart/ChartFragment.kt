package ru.barru.pointschart.presentation.screens.showchart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.barru.pointschart.R
import ru.barru.pointschart.databinding.FragmentChartBinding

/**
 * [Fragment] shows points on screen
 */
class ChartFragment : Fragment() {
    companion object {
        const val EXTRA_POINTS = "ChartFragment.EXTRA_POINTS"
    }

    private val mViewModel by lazy {
        ChartFragmentViewModel(arguments?.getParcelableArrayList(EXTRA_POINTS) ?: arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentChartBinding>(
        inflater, R.layout.fragment_chart,
        null, false
    ).apply {
        vm = mViewModel
        with(list) {
            layoutManager = LinearLayoutManager(list.context, RecyclerView.HORIZONTAL, false)
            adapter = TableAdapter(mViewModel.points.map { TableItemViewModel(it) })
        }
    }?.root

}