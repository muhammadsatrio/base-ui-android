package com.satrio.baseuiandroid

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat

class SatrioText(context: Context, attributeSet: AttributeSet? = null) :
    AppCompatTextView(context, attributeSet) {
    private var style: CustomStyle = CustomStyle.STYLE_DEFAULT
        set(value) {
            field = value
            try {
                TextViewCompat.setTextAppearance(this, value.style)
            } catch (e: Throwable) {
            }
        }

    init {
        attributeSet?.let {
            val typedArray =
                context.obtainStyledAttributes(attributeSet, R.styleable.SatrioText, 0, 0)
            val styleOrdinal = typedArray.getInt(R.styleable.SatrioText_customStyle, 0)
            style = CustomStyle.values()[styleOrdinal]
            typedArray.recycle()
        }
    }
}

enum class CustomStyle(@StyleRes val style: Int) {
    STYLE_DEFAULT(R.style.default_text_style),
    STYLE_INACTIVE(R.style.inactive_text_style)
}