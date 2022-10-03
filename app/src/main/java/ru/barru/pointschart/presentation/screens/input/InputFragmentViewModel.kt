package ru.barru.pointschart.presentation.screens.input

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.barru.pointschart.MainInjector
import ru.barru.pointschart.R
import ru.barru.pointschart.presentation.navigation.NavigationAction
import ru.barru.pointschart.presentation.screens.showchart.ChartFragment.Companion.EXTRA_POINTS
import ru.barru.pointschart.presentation.utils.NumberInputLimit
import ru.barru.pointschart.presentation.utils.NumberInputProcessor
import ru.barru.pointschart.presentation.utils.forceSet

class InputFragmentViewModel(private val mShowChart: NavigationAction) {
    val numberInput = ObservableField("")
    val inputEnabled = ObservableBoolean(true)
    val errorMessage = ObservableInt(0)

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepo by lazy { MainInjector.repo }

    private class InputThrowable(@StringRes val messageStringResId: Int, message: String) :
        Throwable(message)

    fun onNextClick() {
        inputEnabled.set(false)
        mCompositeDisposable.add(Single.just(
            NumberInputProcessor.process(
                numberInput.get(),
                NumberInputLimit.default()
            )
        )
            .subscribeOn(Schedulers.io())
            .flatMap {
                val errorMessageResId = when (it.status) {
                    NumberInputProcessor.TOO_LOW -> R.string.input_error_too_low
                    NumberInputProcessor.TOO_HIGH -> R.string.input_error_too_high
                    NumberInputProcessor.EMPTY -> R.string.input_error_empty
                    NumberInputProcessor.WRONG_FORMAT -> R.string.input_error_wrong_format
                    else -> 0
                }
                if (errorMessageResId > 0) {
                    Single.error(
                        InputThrowable(
                            errorMessageResId,
                            "Wrong user input, status = ${it.status}"
                        )
                    )
                } else {
                    Single.just(it.result)
                }
            }
            .flatMap {
                mRepo.getPoints(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    inputEnabled.set(true)
                    mShowChart(R.id.action_InputFragment_to_ChartFragment, Bundle().apply {
                        putParcelableArrayList(EXTRA_POINTS, it)
                    })
                },
                {
                    inputEnabled.set(true)
                    errorMessage.forceSet(
                        when (it) {
                            is InputThrowable -> it.messageStringResId
                            else -> R.string.server_error
                        }
                    )
                }
            )
        )
    }

    fun release() {
        mCompositeDisposable.clear()
        errorMessage.set(0)
    }
}