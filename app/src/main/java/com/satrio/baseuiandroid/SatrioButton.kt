package com.satrio.baseuiandroid

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.updateLayoutParams
import com.satrio.baseuiandroid.databinding.SatrioButtonBinding

class SatrioButton(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    val binding = SatrioButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        setButtonBackground(R.drawable.button_background)
        setButtonText(context, attributeSet)
        setMargin()
        setTouchListener()
        binding.buttonContainer.isClickable = true
    }

    fun setOnClickListener(function: (() -> Unit)?) {
        binding.buttonContainer.setOnClickListener {
            function?.invoke()
        }
    }

    private fun setTouchListener() {
        binding.buttonContainer.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    doActionClickDown()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    doActionClickUp()
                }
            }
            true
        }
    }

    private fun doActionClickDown() {
        val animatorList = mutableListOf<Animator>()
        animatorList.add(getScaleAnimator(binding.buttonContainer, 1f, 0.9f))

        AnimatorSet().apply {
            playTogether(animatorList)
        }.start()
    }

    private fun doActionClickUp() {
        val animatorList = mutableListOf<Animator>()
        animatorList.add(getScaleAnimator(binding.buttonContainer, 0.9f, 1f))

        AnimatorSet().apply {
            playTogether(animatorList)
        }.start()
    }

    private fun setMargin() {
        binding.buttonText.updateLayoutParams<LinearLayout.LayoutParams> {
            this.topMargin = 10
            this.bottomMargin = 10
            this.leftMargin = 20
            this.rightMargin = 20
        }
    }

    private fun setButtonText(context: Context, attributeSet: AttributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.SatrioButton, 0, 0)
        val text = typedArray.getString(R.styleable.SatrioButton_text).orEmpty()
        binding.buttonText.text = text
    }

    private fun setButtonBackground(@DrawableRes drawableId: Int) {
        val pressed =
            AppCompatResources.getDrawable(context, drawableId)!!.mutate() as GradientDrawable
        val enabled =
            AppCompatResources.getDrawable(context, drawableId)!!.mutate() as GradientDrawable
        val notEnabled =
            AppCompatResources.getDrawable(context, drawableId)!!.mutate() as GradientDrawable

        pressed.color = AppCompatResources.getColorStateList(context, R.color.purple_500)
        enabled.color = AppCompatResources.getColorStateList(context, R.color.teal_500)
        notEnabled.color = AppCompatResources.getColorStateList(context, R.color.gray)

        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressed)
        stateListDrawable.addState(intArrayOf(-android.R.attr.state_enabled), notEnabled)
        stateListDrawable.addState(intArrayOf(android.R.attr.state_enabled), enabled)
        binding.buttonContainer.background = stateListDrawable
    }

    private fun getScaleAnimator(
        view: View,
        from: Float,
        to: Float
    ): Animator {
        return ValueAnimator.ofFloat(from, to).apply {
            duration = 33
            addUpdateListener {
                view.scaleX = (it.animatedValue as Float)
                view.scaleY = (it.animatedValue as Float)
            }
        }
    }
}