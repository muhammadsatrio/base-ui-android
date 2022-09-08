package com.satrio.baseuiandroid

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.satrio.baseuiandroid.databinding.SatrioButtonBinding

class SatrioButton(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    val binding = SatrioButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        setButtonBackground(R.drawable.button_background)
        setButtonText(context, attributeSet)
        binding.buttonContainer.isClickable = true
    }

    fun setOnClickListener(function: (() -> Unit)?) {
        binding.buttonContainer.setOnClickListener {
            function?.invoke()
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

//    private fun getScaleAnimator(
//        view: View,
//        from: Float,
//        to: Float
//    ): Animator {
//        return ValueAnimator.ofFloat(from, to).apply {
//            duration = 33
//            addUpdateListener {
//                view.scaleX = (it.animatedValue as Float)
//                view.scaleY = (it.animatedValue as Float)
//            }
//        }
//    }
}