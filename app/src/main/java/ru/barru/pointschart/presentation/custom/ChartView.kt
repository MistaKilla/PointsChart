package ru.barru.pointschart.presentation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.barru.pointschart.R

/**
 * [View] умеющая рисовать график из точек
 */
class ChartView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private val mPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 1f
        isAntiAlias = true
    }

    private var mData: FloatArray? = null

    private var mIsDataValid = false
    private val mMatrix = Matrix()

    private fun init(attrs: AttributeSet?, defStyleAttr: Int = 0) {
        val styledArray = context.obtainStyledAttributes(attrs, R.styleable.ChartView, defStyleAttr, 0)

        // выдернем из xml возможные настройки для отображения графика
        // line width
        setLineWidth(styledArray.getDimensionPixelSize(R.styleable.ChartView_lineWidth, 1))
        // line color
        setLineColor(styledArray.getColor(R.styleable.ChartView_lineColor, Color.BLACK))
        styledArray.recycle()
    }

    fun setLineColor(color: Int) {
        mPaint.color = color
    }

    fun setLineWidth(width: Int) {
        mPaint.strokeWidth = width.toFloat()
    }

    fun setData(newData: FloatArray) {
        mData = newData
        mIsDataValid = false
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mData?.let { data ->
            if (!mIsDataValid) {
                mIsDataValid = true

                // соберем минимальные и максимальные координаты точек линий
                // чтобы вписать их в область экрана занятую View
                var minX = Float.POSITIVE_INFINITY
                var maxX = Float.NEGATIVE_INFINITY
                var minY = Float.POSITIVE_INFINITY
                var maxY = Float.NEGATIVE_INFINITY
                for (i in data.indices step 2) {
                    if (data[i] < minX) minX = data[i]
                    if (data[i] > maxX) maxX = data[i]
                    if (data[i + 1] < minY) minY = data[i + 1]
                    if (data[i + 1] > maxY) maxY = data[i + 1]
                }

                // могучая матричная магия сделает для нас расчет скалирования
                // из исходных данных к реальном размеру View
                mMatrix.setPolyToPoly(
                    floatArrayOf(
                        minX, minY,
                        maxX, minY,
                        maxX, maxY,
                        minX, maxY
                    ),
                    0,
                    floatArrayOf(
                        0f, 0f,
                        measuredWidth.toFloat(), 0f,
                        measuredWidth.toFloat(), measuredHeight.toFloat(),
                        0f, measuredHeight.toFloat()
                    ),
                    0,
                    4
                )

                // обновим координаты в нашем массиве, и в следующем onDraw они уже впишутся в экран
                mMatrix.mapPoints(data)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        mData?.let { canvas?.drawLines(it, mPaint) }
    }
}