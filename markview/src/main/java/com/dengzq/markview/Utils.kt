package com.dengzq.markview

import android.content.Context

/**
 * <p>author    dengzq</P>
 * <p>date      2018/8/22 下午11:46</p>
 * <p>package   com.dengzq.markview</p>
 * <p>readMe    TODO</p>
 */
fun dp2px(context: Context, dp: Float): Float = context.resources.displayMetrics.density * dp + 0.5f

fun sp2px(context: Context, sp: Float): Float = context.resources.displayMetrics.scaledDensity * sp + 0.5f