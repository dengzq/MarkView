package com.dengzq.demo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dengzq.markview.Display
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ticket1.mode = Display.MODE_IMG_NUM
        ticket1.number = "120"
        ticket1.image=R.mipmap.icon_bag
        ticket1.tipSize = resources.displayMetrics.scaledDensity * 25
        ticket1.tipBgEnable = false
        ticket1.tipColor = Color.RED
        ticket1.xOffset = (-1 * resources.displayMetrics.density * 5).toInt()
        ticket1.setBackgroundColor(Color.parseColor("#e3e3e3"))

        ticket2.mode = Display.MODE_IMG_NUM
        ticket2.number = "120"
        ticket2.image = R.mipmap.icon_bag
        ticket2.tipSize = resources.displayMetrics.scaledDensity * 13
        ticket2.tipColor = Color.WHITE

        ticket3.mode = Display.MODE_IMG_NUM
        ticket3.number = "48"
        ticket3.image = R.mipmap.icon_bag
        ticket3.tipSize = resources.displayMetrics.scaledDensity * 16
        ticket3.tipColor = Color.WHITE

        ticket4.mode = Display.MODE_IMG_DOT
        ticket4.image = R.mipmap.icon_bag
        ticket4.dotSize = resources.displayMetrics.scaledDensity * 8
        ticket4.tipColor = Color.RED

        ticket5.mode = Display.MODE_TEXT_DOT
        ticket5.dotSize = resources.displayMetrics.scaledDensity * 4
        ticket5.tipColor = Color.RED
        ticket5.text = "Only you"
        ticket5.getContentTextView()?.textSize = resources.displayMetrics.scaledDensity * 15
        ticket5.setBackgroundColor(Color.parseColor("#f2f2f2"))

        ticket6.mode = Display.MODE_TEXT_NUM
        ticket6.tipSize = resources.displayMetrics.density * 30
        ticket6.tipBorderEnable = true
        ticket6.number = "120"
        ticket6.text = "My Live"
        ticket6.getContentTextView()?.textSize = resources.displayMetrics.scaledDensity * 20
        ticket6.tipColor = Color.WHITE
        ticket6.xOffset = (-1 * resources.displayMetrics.density * 10).toInt()
        ticket6.yOffset = (-1 * resources.displayMetrics.density * 10).toInt()
        ticket6.setBackgroundColor(Color.parseColor("#e3e3e3"))
    }
}
