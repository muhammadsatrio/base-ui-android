package com.satrio.baseuiandroid

import android.content.Context
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
    }

    private fun setButtonText(context: Context, attributeSet: AttributeSet) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.SatrioButton, 0, 0)
        val text = typedArray.getString(R.styleable.SatrioButton_text).orEmpty()
        binding.buttonText.text = text
    }

    private fun setButtonBackground(@DrawableRes drawableId: Int) {
        binding.buttonContainer.background = AppCompatResources.getDrawable(context, drawableId)
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