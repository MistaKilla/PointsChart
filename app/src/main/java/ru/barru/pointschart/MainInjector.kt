package ru.barru.pointschart

import ru.barru.pointschart.data.api.Api
import ru.barru.pointschart.data.repo.Repo

/**
 * DI imitation
 */
object MainInjector {
    private val mApi by lazy { Api() }
    val repo by lazy { Repo(mApi) }
}