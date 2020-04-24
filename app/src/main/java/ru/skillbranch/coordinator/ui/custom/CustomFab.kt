package ru.skillbranch.coordinator.ui.custom

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.Checkable
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.skillbranch.coordinator.R

class CustomFab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FloatingActionButton(context, attrs, defStyleAttr), Checkable {
    private var checked : Boolean = false
    private val animation : AnimatorSet

    init {
        val ca = context.getColor(R.color.color_accent)
        val cw = context.getColor(android.R.color.white)

        val rotateAnim = ObjectAnimator.ofFloat(this, "rotation", 135f)
        val iconAnim = ValueAnimator.ofArgb(cw, ca)
        iconAnim.addUpdateListener { imageTintList = ColorStateList.valueOf(it.animatedValue as Int) }
        val bgAnim = ValueAnimator.ofArgb(ca, cw)
        bgAnim.addUpdateListener { backgroundTintList = ColorStateList.valueOf(it.animatedValue as Int) }

        animation = AnimatorSet().apply {
            interpolator = FastOutSlowInInterpolator()
            playTogether(rotateAnim, iconAnim, bgAnim)
        }
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun isChecked(): Boolean = checked

    override fun toggle() {
        isChecked = !checked
    }

    override fun setChecked(check: Boolean) {
        if(checked == check) return
        checked = check
        playAnimation()
    }

    private fun playAnimation() {
        if(isChecked) animation.start() else animation.reverse()
    }
}