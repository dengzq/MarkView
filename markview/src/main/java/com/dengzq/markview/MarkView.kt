package com.dengzq.markview

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

/**
 * <p>author    dengzq</P>
 * <p>date      2018/8/22 下午3:58</p>
 * <p>package   com.dengzq.markview</p>
 * <p>readMe    TicketView</p>
 */
class MarkView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    private val textView: TextView = TextView(context)
    private val imageView: ImageView = ImageView(context)
    private val numberView: NumberView = NumberView(context)

    var mode: Display = Display.MODE_IMG_DOT
        set(value) {
            field = value
            notifyLayout()
            invalidate()
        }

    var text: CharSequence = ""
        set(value) {
            field = value
            textView.text = text
        }

    @ColorRes
    var textColor: Int = Color.BLACK
        set(value) {
            field = value
            textView.setTextColor(field)
        }

    var image: Int? = null
        set(value) {
            field = value
            field?.let {
                imageView.setImageResource(it)
            }
        }

    var xOffset: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    var yOffset: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    var dotSize: Float = dp2px(context, 3.0f)
        set(value) {
            field = value
            numberView.dotSize = field
            invalidate()
        }

    var number: String = ""
        set(value) {
            field = value
            numberView.number = field
            invalidate()
        }

    var tipSize: Float = dp2px(context, 16.0f)
        set(value) {
            field = value
            numberView.tipSize = field
            invalidate()
        }

    var tipColor: Int = Color.WHITE
        set(value) {
            field = value
            numberView.textColor = field
            invalidate()
        }

    var tipBgColor: Int = Color.RED
        set(value) {
            field = value
            numberView.bgColor = field
            invalidate()
        }

    var tipBorderColor: Int = Color.WHITE
        set(value) {
            field = value
            numberView.borderColor = tipBorderColor
            invalidate()
        }

    var boderWidth: Float = dp2px(context, 1.0f)
        set(value) {
            field = value
            numberView.borderWidth = field
            invalidate()
        }

    var tipLeftRightMargin: Float = numberView.leftRightMargin
        set(value) {
            field = value
            numberView.leftRightMargin
            invalidate()
        }

    var tipTopBomMargin: Float = numberView.topBtmMargin
        set(value) {
            field = value
            numberView.topBtmMargin = field
            invalidate()
        }

    var tipBorderEnable = numberView.borderEnable
        set(value) {
            field = value
            numberView.borderEnable = field
            invalidate()
        }

    var tipBgEnable = numberView.backupEnable
        set(value) {
            field = value
            numberView.backupEnable = field
            invalidate()
        }

    var emptyVisible = false
        set(value) {
            field = value
            numberView.emptyVisible = field
            invalidate()
        }

    fun getContentTextView(): TextView? {
        for (i in 0..childCount) {
            if (getChildAt(i) is TextView)
                return getChildAt(i) as TextView
        }
        return null
    }

    fun getContentImageView(): ImageView? {
        for (i in 0..childCount) {
            if (getChildAt(i) is ImageView)
                return getChildAt(i) as ImageView
        }
        return null
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        notifyLayout()
    }

    private fun notifyLayout() {
        when (mode) {
            Display.MODE_TEXT_NON -> layoutModeTextNon()
            Display.MODE_TEXT_DOT -> layoutModeTextDot()
            Display.MODE_TEXT_NUM -> layoutModeTextNum()
            Display.MODE_IMG_NON -> layoutModeImgNon()
            Display.MODE_IMG_DOT -> layoutModeImgDot()
            Display.MODE_IMG_NUM -> layoutModeImgNum()
            else -> removeAllViews()
        }
    }

    private fun layoutModeImgNon() {
        if (textView.parent != null) removeView(textView)
        if (numberView.parent != null) removeView(numberView)
        if (imageView.parent == null) addView(imageView)
    }

    private fun layoutModeTextNon() {
        if (imageView.parent != null) removeView(imageView)
        if (numberView.parent != null) removeView(numberView)
        if (textView.parent == null) addView(textView)
    }

    private fun layoutModeTextDot() {
        if (imageView.parent != null) removeView(imageView)
        if (textView.parent == null) addView(textView)
        if (numberView.parent == null) addView(numberView, generateLayoutParams())

        numberView.mode = TipMode.MODE_DOT
        val var1 = numberView.layoutParams as FrameLayout.LayoutParams
        var1.leftMargin = textView.measuredWidth + (textView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
        numberView.layoutParams = var1

        val var2 = textView.layoutParams as FrameLayout.LayoutParams
        var2.topMargin = numberView.measuredHeight / 2 + (numberView.layoutParams as FrameLayout.LayoutParams).topMargin + yOffset
        textView.layoutParams = var2
    }

    private fun layoutModeTextNum() {
        if (imageView.parent != null) removeView(imageView)
        if (textView.parent == null) addView(textView)
        if (numberView.parent == null) addView(numberView, generateLayoutParams())

        numberView.mode = TipMode.MODE_NUM
        val var1 = numberView.layoutParams as FrameLayout.LayoutParams
        var1.leftMargin = textView.measuredWidth + (textView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
        numberView.layoutParams = var1

        val var2 = textView.layoutParams as FrameLayout.LayoutParams
        var2.topMargin = numberView.measuredHeight / 2 + (numberView.layoutParams as FrameLayout.LayoutParams).topMargin + yOffset
        textView.layoutParams = var2
    }

    private fun layoutModeImgDot() {
        if (textView.parent != null) removeView(textView)
        if (imageView.parent == null) addView(imageView, generateLayoutParams())
        if (numberView.parent == null) addView(numberView, generateLayoutParams())

        numberView.mode = TipMode.MODE_DOT
        val var1 = numberView.layoutParams as FrameLayout.LayoutParams
        var1.leftMargin = imageView.measuredWidth + (imageView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
        numberView.layoutParams = var1

        val var2 = imageView.layoutParams as FrameLayout.LayoutParams
        var2.topMargin = numberView.measuredHeight / 2 + (numberView.layoutParams as FrameLayout.LayoutParams).topMargin + yOffset
        imageView.layoutParams = var2
    }

    private fun layoutModeImgNum() {
        if (textView.parent != null) removeView(textView)
        if (imageView.parent == null) addView(imageView, generateLayoutParams())
        if (numberView.parent == null) addView(numberView, generateLayoutParams())

        numberView.mode = TipMode.MODE_NUM
        val var1 = numberView.layoutParams as FrameLayout.LayoutParams
        var1.leftMargin = imageView.measuredWidth + (imageView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
        numberView.layoutParams = var1

        val var2 = imageView.layoutParams as FrameLayout.LayoutParams
        var2.topMargin = numberView.measuredHeight / 2 + (numberView.layoutParams as FrameLayout.LayoutParams).topMargin + yOffset
        imageView.layoutParams = var2
    }

    private fun generateLayoutParams(): FrameLayout.LayoutParams = FrameLayout.LayoutParams(-2, -2)
}