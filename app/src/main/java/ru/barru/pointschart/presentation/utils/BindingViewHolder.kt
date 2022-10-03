package ru.barru.pointschart.presentation.utils

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Обертка для [RecyclerView.ViewHolder] для работы через DataBinding
 */
class BindingViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)