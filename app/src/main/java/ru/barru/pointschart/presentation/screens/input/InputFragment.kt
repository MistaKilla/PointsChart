package ru.barru.pointschart.presentation.screens.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.barru.pointschart.R
import ru.barru.pointschart.databinding.FragmentInputBinding

/**
 * [Fragment] awaits for user number input and requests points
 */
class InputFragment : Fragment() {

    private val mViewModel by lazy {
        InputFragmentViewModel { action, bundle ->
            findNavController().navigate(
                action,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentInputBinding>(
        inflater, R.layout.fragment_input,
        null, false
    ).apply {
        vm = mViewModel
    }?.root

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.release()
    }
}