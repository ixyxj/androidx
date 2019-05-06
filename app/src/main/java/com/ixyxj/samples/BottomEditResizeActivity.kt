package com.ixyxj.samples;

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * For more information, you can visit https://github.com/ixyxj,
 * or contact me by xyxjun@gmail.com
 *
 * @author silen on 2019/5/7 0:23
 * Copyright (c) 2019 in FORETREE
 */
class BottomEditResizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏底部按钮就不能弹起
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val contentView = layoutInflater.inflate(R.layout.activity_bottom_edit_resize, null)
        this.setContentView(contentView)
    }

    override fun setContentView(view: View?) {
        val container = LinearLayoutCompat2(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(view, layoutParams)
        }
        super.setContentView(container)
    }
}

class LinearLayoutCompat2: LinearLayoutCompat {
    private var mInsets = IntArray(4)

    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet): super(context, attr)

    override fun computeSystemWindowInsets(`in`: WindowInsets?, outLocalInsets: Rect?): WindowInsets {
        outLocalInsets?.run {
            left = 0
            top = 0
            right = 0
        }
        return super.computeSystemWindowInsets(`in`, outLocalInsets)
    }

    override fun fitSystemWindows(insets: Rect?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            insets?.run {
                mInsets[0] = left
                mInsets[1] = top
                mInsets[2] = right
                left = 0
                top = 0
                right = 0
            }
        }
        return super.fitSystemWindows(insets)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(true, l, t, r, b)
    }
}
