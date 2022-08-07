package space.erictriawan.kontak.utils

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable


class CircleDrawable(
    private val fillColor: Int,
    private val strokeColor: Int,
    private val radius: Float
) :
    Drawable() {
    private val circlePaint: Paint
    override fun draw(canvas: Canvas) {
        val x = bounds.centerX()
        val y = bounds.centerY()
        //draw fill color circle
        circlePaint.style = Paint.Style.FILL
        circlePaint.color = fillColor
        canvas.drawCircle(x.toFloat(), y.toFloat(), radius, circlePaint)
        // draw stroke circle
        circlePaint.style = Paint.Style.STROKE
        circlePaint.color = strokeColor
        circlePaint.strokeWidth = 5f
        canvas.drawCircle(x.toFloat(), y.toFloat(), radius, circlePaint)
    }

    override fun setAlpha(alpha: Int) {
        circlePaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        circlePaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    init {
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }
}