package com.codequest.animatedrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AnimatedRecyclerView @JvmOverloads constructor(
    private val startAnimationMargin: Float,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val itemHolders: MutableList<AnimatedItemHolder> = mutableListOf()

    fun onAddViewHolder(animatedItemHolder: AnimatedItemHolder) {
        itemHolders.add(animatedItemHolder)
    }

    fun onRemoveViewHolder(animatedItemHolder: AnimatedItemHolder) {
        itemHolders.remove(animatedItemHolder)
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        itemHolders.forEach {
            when {
                it.itemView.shouldStartShowAnimation() -> it.startShowAnimation()
                it.itemView.shouldStartHideAnimation() -> it.startHideAnimation()
            }
        }
    }

    private fun View.shouldStartShowAnimation(): Boolean {
        val viewPosition = getAbsoluteCenterY()
        return viewPosition >= startAnimationMargin && viewPosition <= height - startAnimationMargin
    }

    private fun View.shouldStartHideAnimation(): Boolean {
        val viewPosition = getAbsoluteCenterY()
        return viewPosition < startAnimationMargin || viewPosition > height - startAnimationMargin
    }
}
