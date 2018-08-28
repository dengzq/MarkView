package com.dengzq.demo

import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.dengzq.demo.databinding.ActivityMainBinding
import com.dengzq.markview.Display
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var observableNumber = ObservableInt(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.activity = this

        ticket1.mode = Display.MODE_IMG_NUM
        ticket1.tipNumber = "120"
        ticket1.contentImg = ContextCompat.getDrawable(this, R.mipmap.icon_bag)
        ticket1.tipSize = resources.displayMetrics.scaledDensity * 25
        ticket1.tipBgEnable = false
        ticket1.tipTextColor = Color.RED
        ticket1.xOffset = (-1 * resources.displayMetrics.density * 5).toInt()
        ticket1.setBackgroundColor(Color.parseColor("#e3e3e3"))

        ticket2.mode = Display.MODE_IMG_NUM
        ticket2.tipNumber = "120"
        ticket2.contentImg = ContextCompat.getDrawable(this, R.mipmap.icon_bag)
        ticket2.tipSize = resources.displayMetrics.scaledDensity * 13
        ticket2.tipTextColor = Color.WHITE

        ticket3.mode = Display.MODE_IMG_NUM
        ticket3.tipNumber = "48"
        ticket3.contentImg = ContextCompat.getDrawable(this, R.mipmap.icon_bag)
        ticket3.tipSize = resources.displayMetrics.scaledDensity * 16
        ticket3.tipTextColor = Color.WHITE

        ticket4.mode = Display.MODE_IMG_DOT
        ticket4.contentImg = ContextCompat.getDrawable(this, R.mipmap.icon_bag)
        ticket4.dotSize = resources.displayMetrics.scaledDensity * 8
        ticket4.tipTextColor = Color.RED

        ticket5.mode = Display.MODE_TEXT_DOT
        ticket5.dotSize = resources.displayMetrics.scaledDensity * 4
        ticket5.tipTextColor = Color.RED
        ticket5.contentText = "Only you"
        ticket5.getContentTextView()?.textSize = resources.displayMetrics.scaledDensity * 15
        ticket5.setBackgroundColor(Color.parseColor("#f2f2f2"))

        ticket6.mode = Display.MODE_TEXT_NUM
        ticket6.tipSize = resources.displayMetrics.density * 30
        ticket6.tipStrokeEnable = true
        ticket6.tipNumber = "120"
        ticket6.contentText = "My Live"
        ticket6.getContentTextView()?.textSize = resources.displayMetrics.scaledDensity * 20
        ticket6.tipTextColor = Color.WHITE
        ticket6.xOffset = (-1 * resources.displayMetrics.density * 10).toInt()
        ticket6.yOffset = (-1 * resources.displayMetrics.density * 10).toInt()
        ticket6.setBackgroundColor(Color.parseColor("#e3e3e3"))
    }

    fun addNumber() {
        val var1 = observableNumber.get() + 6
        observableNumber.set(var1)

        val r = Math.random() * 255.toFloat()
        val g = Math.random() * 255.toFloat()
        val b = Math.random() * 255.toFloat()
        val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Color.rgb(r.toFloat(), g.toFloat(), b.toFloat())
        } else {
            Color.RED
        }
        ticket7.tipBgColor = color
        if (observableNumber.get()<99){
            ticket7.tipTbMargin+=1
            ticket7.tipLrMargin+=2.5f
        }

    }
}
