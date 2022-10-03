package ru.barru.pointschart.presentation.screens.showchart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.barru.pointschart.R
import ru.barru.pointschart.databinding.TableItemBinding
import ru.barru.pointschart.presentation.utils.BindingViewHolder

class TableAdapter(private val mPoints: List<TableItemViewModel>) :
    RecyclerView.Adapter<BindingViewHolder<TableItemBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<TableItemBinding> =
        BindingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.table_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: BindingViewHolder<TableItemBinding>, position: Int) {
        holder.binding.vm = mPoints[position]
    }

    override fun getItemCount() = mPoints.size
}