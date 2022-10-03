package ru.barru.pointschart.presentation.utils

object NumberInputProcessor {
    const val OK = 1
    const val WRONG_FORMAT = 2
    const val TOO_LOW = 3
    const val TOO_HIGH = 4
    const val EMPTY = 5

    data class Result(val status: Int, val result: Int = 0)

    /**
     * Проверка пользовательского ввода строки
     * на то что она число и попадет в некоторые лимиты
     * @param value текстовое значение для проверки
     * @param limits опциональные лимиты для проверки
     * @return результат проверки со статусом и числовым результатом, если преобразование в число было успешным
     */
    fun process(value: String?, limits: NumberInputLimit?): Result = value?.let {
        if (it.isEmpty()) {
            Result(EMPTY)
        } else {
            it.toIntOrNull()?.let { number ->
                limits?.let {
                    when {
                        number < it.min -> Result(TOO_LOW)
                        number > it.max -> Result(TOO_HIGH)
                        else -> Result(OK, number)
                    }
                } ?: Result(OK, number)
            } ?: Result(WRONG_FORMAT)
        }
    } ?: Result(EMPTY)
}