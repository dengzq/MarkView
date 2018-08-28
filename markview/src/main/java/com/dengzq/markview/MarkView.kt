@file:Suppress("MemberVisibilityCanBePrivate")

package com.dengzq.markview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

/**
 * <p>author    dengzq</P>
 * <p>date      2018/8/22 下午3:58</p>
 * <p>package   com.dengzq.markview</p>
 * <p>readMe    MarkView</p>
 */
class MarkView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attributeSet, defStyleAttr) {

    private val textView: TextView = TextView(context)
    private val imageView: ImageView = ImageView(context)
    private val numberView: NumberView = NumberView(context)

    var contentTextSize: Float = 14.0f
        set(value) {
            field = value
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            invalidate()
        }

    @ColorRes
    var contentTextColor: Int = Color.DKGRAY
        set(value) {
            field = value
            textView.setTextColor(value)
        }

    //Display mode of mark view;
    var mode: Display = Display.MODE_IMG_DOT
        set(value) {
            field = value
            notifyLayout()
            invalidate()
        }

    var contentText: CharSequence = ""
        set(value) {
            field = value
            textView.text = field
        }

    @ColorRes
    var textColor: Int = Color.BLACK
        set(value) {
            field = value
            textView.setTextColor(field)
        }

    var contentImg: Drawable? = null
        set(value) {
            field = value
            field?.let {
                imageView.setImageDrawable(it)
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

    var tipNumber: String = ""
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

    var tipTextColor: Int = Color.WHITE
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

    var tipStrokeColor: Int = Color.WHITE
        set(value) {
            field = value
            numberView.borderColor = field
            invalidate()
        }

    var tipStrokeWidth: Float = dp2px(context, 1.0f)
        set(value) {
            field = value
            numberView.borderWidth = field
            invalidate()
        }

    var tipLrMargin: Float = numberView.lrMargin
        set(value) {
            field = value
            numberView.lrMargin = field
            invalidate()
        }

    var tipTbMargin: Float = numberView.tbMargin
        set(value) {
            field = value
            numberView.tbMargin = field
            invalidate()
        }

    var tipStrokeEnable = numberView.borderEnable
        set(value) {
            field = value
            numberView.borderEnable = field
            invalidate()
        }

    var tipBgEnable = numberView.bgEnable
        set(value) {
            field = value
            numberView.bgEnable = field
            invalidate()
        }

    var emptyVisible = false
        set(value) {
            field = value
            numberView.emptyVisible = field
            invalidate()
        }

    init {
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.MarkView)
        tipSize = typeArray.getDimension(R.styleable.MarkView_tipSize, dp2px(context, 16.0f))
        dotSize = typeArray.getDimension(R.styleable.MarkView_dotSize, dp2px(context, 3.0f))
        tipTextColor = typeArray.getColor(R.styleable.MarkView_tipTextColor, Color.WHITE)
        tipBgColor = typeArray.getColor(R.styleable.MarkView_tipBgColor, Color.RED)
        tipStrokeColor = typeArray.getColor(R.styleable.MarkView_tipStrokeColor, Color.WHITE)
        tipStrokeWidth = typeArray.getDimension(R.styleable.MarkView_tipStrokeWidth, dp2px(context, 1.0f))
        contentTextSize = typeArray.getDimensionPixelOffset(R.styleable.MarkView_contentTextSize, 20).toFloat()
        contentTextColor = typeArray.getColor(R.styleable.MarkView_contentTextColor, Color.DKGRAY)
        contentText = typeArray.getText(R.styleable.MarkView_contentText) ?: ""
        contentImg = typeArray.getDrawable(R.styleable.MarkView_contentImg)
        tipNumber = typeArray.getString(R.styleable.MarkView_tipNumber) ?: ""
        tipBgEnable = typeArray.getBoolean(R.styleable.MarkView_tipBgEnable, true)
        tipStrokeEnable = typeArray.getBoolean(R.styleable.MarkView_tipStrokeEnable, false)
        emptyVisible = typeArray.getBoolean(R.styleable.MarkView_emptyVisible, false)
        xOffset = typeArray.getDimension(R.styleable.MarkView_xOffset, 0.0f).toInt()
        yOffset = typeArray.getDimension(R.styleable.MarkView_yOffset, 0.0f).toInt()
        tipLrMargin = typeArray.getDimension(R.styleable.MarkView_tipLrMargin, tipLrMargin)
        tipTbMargin = typeArray.getDimension(R.styleable.MarkView_tipTbMargin, tipTbMargin)
        val markMode = typeArray.getInt(R.styleable.MarkView_markMode, 0)
        when (markMode) {
            0 -> mode = Display.MODE_DEFAULT
            1 -> mode = Display.MODE_TEXT_NON
            2 -> mode = Display.MODE_TEXT_DOT
            3 -> mode = Display.MODE_TEXT_NUM
            4 -> mode = Display.MODE_IMG_NON
            5 -> mode = Display.MODE_IMG_DOT
            6 -> mode = Display.MODE_IMG_NUM
        }
        typeArray.recycle()
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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
        var1.marginStart = textView.measuredWidth + (textView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
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
        var1.marginStart = textView.measuredWidth + (textView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
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
        var1.marginStart = imageView.measuredWidth + (imageView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
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
        var1.marginStart = imageView.measuredWidth + (imageView.layoutParams as FrameLayout.LayoutParams).leftMargin + xOffset
        numberView.layoutParams = var1

        val var2 = imageView.layoutParams as FrameLayout.LayoutParams
        var2.topMargin = numberView.measuredHeight / 2 + (numberView.layoutParams as FrameLayout.LayoutParams).topMargin + yOffset
        imageView.layoutParams = var2
    }

    private fun generateLayoutParams(): FrameLayout.LayoutParams = FrameLayout.LayoutParams(-2, -2)
}