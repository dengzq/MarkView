@file:Suppress("MemberVisibilityCanBePrivate")

package com.dengzq.markview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import java.util.regex.Pattern

/**
 * <p>author    dengzq</P>
 * <p>date      2018/8/7 下午11:43</p>
 * <p>package   com.dengzq.markview</p>
 * <p>readMe    NumberView</p>
 */
class NumberView : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    companion object {
        private const val MAX_NUM: String = "99+"
    }

    private var itemWidth: Int = 0
    private var itemHeight: Int = 0
    private var textWidth: Int = 0
    private var textHeight: Int = 0

    var bgColor: Int = Color.RED
        set(value) {
            field = value
            invalidate()
        }
    var textColor: Int = Color.WHITE
        set(value) {
            field = value
            textPaint.color = field
            invalidate()
        }

    var borderColor: Int = Color.WHITE
        set(value) {
            field = value
            invalidate()
        }

    var tipSize: Float = sp2px(context, 16.0f)
        set(value) {
            field = value
            invalidate()
        }

    var dotSize: Float = dp2px(context, 3.0f)
        set(value) {
            field = value
            invalidate()
        }

    var borderWidth: Float = dp2px(context, 2.0f)
        set(value) {
            field = value
            invalidate()
        }

    var leftRightMargin: Float = tipSize * 1.0f / 20
        set(value) {
            field = value
            invalidate()
        }

    var topBtmMargin: Float = tipSize * 1.0f / 20
        set(value) {
            field = value
            invalidate()
        }

    var number: String = ""
        set(value) {
            field = value
            invalidate()
        }

    internal var mode: TipMode = TipMode.MODE_NUM
        set(value) {
            field = value
            invalidate()
        }
    var borderEnable: Boolean = false
        set(value) {
            field = value
            invalidate()
        }
    var backupEnable: Boolean = true
        set(value) {
            field = value
            invalidate()
        }

    var emptyVisible: Boolean = false

    private val bgPaint = Paint().apply {
        color = bgColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        color = textColor
        isAntiAlias = true
        isDither = true
        textAlign = Paint.Align.CENTER
        style = Paint.Style.STROKE
        textSize = tipSize
    }

    private val borderPaint = Paint().apply {
        color = borderColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureSize()
        setMeasuredDimension(itemWidth, itemHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        if (mode == TipMode.MODE_DOT)
            drawDot(canvas)
        else if (mode == TipMode.MODE_NUM) {
            drawText(canvas)
        }
    }

    private fun drawText(canvas: Canvas?) {
        canvas?.let {
            if (!emptyVisible && number != MAX_NUM && number.toInt() == 0) return
            val circle = itemWidth == itemHeight
            if (circle) {
                val width: Float = if (borderEnable) itemWidth - borderWidth * 2 else itemWidth.toFloat()
                val centerX = itemWidth * 1.0f / 2
                val centerY = itemHeight * 1.0f / 2
                //是否绘制边框
                if (borderEnable) {
                    canvas.drawCircle(itemWidth / 2.toFloat(), itemHeight / 2.toFloat(), itemWidth / 2.toFloat(), borderPaint)
                }
                //是否绘制背景
                if (backupEnable) {
                    canvas.drawCircle(centerX, centerY, width / 2, bgPaint)
                }

                textPaint.textSize = width * 2 / 3
                val rect = getMeasureBounds(number, textPaint.textSize)
                canvas.drawText(number, centerX, (itemHeight / 2 + rect.height() / 2).toFloat(), textPaint)

            } else {
                val height: Float = if (borderEnable) itemHeight - borderWidth * 2 else itemHeight.toFloat()
                //是否绘制边框
                if (borderEnable) {
                    val rect = Rect()
                    rect.set(itemWidth / 2 - textWidth / 2, 0, itemWidth / 2 + textWidth / 2, itemHeight)
                    canvas.drawRect(rect, borderPaint)
                    canvas.drawCircle((itemWidth / 2 - textWidth / 2).toFloat(), itemHeight / 2.toFloat(), itemHeight / 2.toFloat(), borderPaint)
                    canvas.drawCircle((itemWidth / 2 + textWidth / 2).toFloat(), itemHeight / 2.toFloat(), itemHeight / 2.toFloat(), borderPaint)
                }
                //是否绘制背景
                if (backupEnable) {
                    val rect = Rect()
                    rect.set(itemWidth / 2 - textWidth / 2, ((itemHeight - height) / 2).toInt(), itemWidth / 2 + textWidth / 2, itemHeight - ((itemHeight - height) / 2).toInt())
                    canvas.drawRect(rect, bgPaint)
                    canvas.drawCircle((itemWidth / 2 - textWidth / 2).toFloat(), itemHeight / 2.toFloat(), height / 2.toFloat(), bgPaint)
                    canvas.drawCircle((itemWidth / 2 + textWidth / 2).toFloat(), itemHeight / 2.toFloat(), height / 2.toFloat(), bgPaint)
                }

                textPaint.textSize = tipSize
                canvas.drawText(number, itemWidth / 2.toFloat(), (itemHeight / 2 + textHeight / 2).toFloat(), textPaint)
            }
        }
    }

    private fun drawDot(canvas: Canvas?) {
        canvas?.let {
            val centerX = itemWidth * 1.0f / 2
            val centerY = itemHeight * 1.0f / 2

            bgPaint.style = Paint.Style.FILL
            canvas.drawCircle(centerX, centerY, centerX, bgPaint)
        }
    }

    private fun getMeasureBounds(number: String, textSize: Float): Rect {
        textPaint.textSize = textSize
        val var1 = Rect()
        textPaint.getTextBounds(number, 0, number.length, var1)
        return var1
    }

    private fun measureSize() {
        checkNum()
        val var1 = getMeasureBounds(number, tipSize)
        val var2 = getMeasureBounds("00", tipSize)

        when (mode) {
            TipMode.MODE_DEF -> {
                itemWidth = 0
                itemHeight = 0
            }
            TipMode.MODE_DOT -> {
                itemWidth = dotSize.toInt()
                itemHeight = dotSize.toInt()
            }
            TipMode.MODE_NUM -> {

                textWidth = var1.width()
                textHeight = var1.height()

                if (!borderEnable && !backupEnable) {
                    itemWidth = textWidth
                    itemHeight = textHeight
                    return
                }

                if (number == MAX_NUM) {
                    itemHeight = (Math.max(var1.height(), var2.width()) + topBtmMargin * 2).toInt()
                    itemWidth = var1.width() + itemHeight
                } else {
                    itemWidth = (Math.max(var1.width(), var1.height()) + leftRightMargin * 2).toInt()
                    itemHeight = (Math.max(var1.width(), var1.height()) + topBtmMargin * 2).toInt()
                }
            }
        }
    }

    private fun checkNum() {
        if (number == MAX_NUM) return
        if (number.isEmpty()) {
            number = "0"
            return
        }

        val pattern = Pattern.compile("^[0-9]\\d*$")
        if (!pattern.matcher(number).matches()) {
            throw IllegalArgumentException("Number should be positive integer,now is : $number")
        }

        if (number.toInt() > 99) number = MAX_NUM
    }
}