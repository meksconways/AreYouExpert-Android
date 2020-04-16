package com.meksconway.areyouexpert.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.bumptech.glide.Glide
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.util.px


class CategoryView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var _startColor: Int
    private var _endColor: Int
    private val _cornerRadius: Float
    private val _title: String
    private val _icon: Drawable?

    private var _imageView: ImageView
    private var _titleText: TextView



    init {
        View.inflate(context, R.layout.view_category, this)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CategoryView, 0, 0
        ).apply {

            try {
                _startColor = this.getColor(R.styleable.CategoryView__startColor, 0xffffff)
                _endColor = this.getColor(R.styleable.CategoryView__endColor, 0xffffff)
                _cornerRadius = this.getFloat(R.styleable.CategoryView__cornerRadius, 0f)
                _title = this.getString(R.styleable.CategoryView__title) ?: ""
                _icon = this.getDrawable(R.styleable.CategoryView__icon)

                _imageView = findViewById(R.id.imgCategory)
                _titleText = findViewById(R.id.txtCatTitle)


            } finally {
                recycle()
            }

        }
    }

    fun setImage(@DrawableRes resource: Int) {
//        _imageView.setImageResource(resource)
        Glide.with(context)
            .asBitmap()
            .load(resource)
            .into(_imageView)
//        invalidate()
//        requestLayout()
    }

    fun setTitle(title: String) {
        _titleText.text = title
        invalidate()
        requestLayout()
    }

    fun setImageTint(@ColorRes tintColor: Int) {
        _imageView.setColorFilter(
            ContextCompat.getColor(context, tintColor),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        invalidate()
        requestLayout()
    }

    fun setGradient(@ColorRes startColor: Int, @ColorRes endColor: Int, radius: Int = 8) {
        _startColor = startColor
        _endColor = endColor
        val first = ContextCompat.getColor(context, _startColor)
        val end = ContextCompat.getColor(context, _endColor)
        val gd = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(first, end)
        )
        gd.gradientRadius = 90f
        gd.cornerRadius = radius.toFloat().px

        val alphaColor = ColorUtils.setAlphaComponent(first, 120)
        val list = ColorStateList(arrayOf(intArrayOf()), intArrayOf(alphaColor))
        val rippleDrawable = RippleDrawable(list, gd, null)

        this.background = rippleDrawable

        invalidate()
        requestLayout()
    }


}