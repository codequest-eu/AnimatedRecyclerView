package com.codequest.animatedrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AnimatedRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private val startAnimationMargin: Float

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.AnimatedRecyclerView,
                0,
                0
            )
            .apply {
                try {
                    startAnimationMargin =
                        getDimension(R.styleable.AnimatedRecyclerView_startAnimationOffset, 100f)
                } finally {
                    recycle()
                }
            }
    }

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
        val viewPosition = getCenterY()
        return viewPosition >= this@AnimatedRecyclerView.top + startAnimationMargin && viewPosition <= this@AnimatedRecyclerView.bottom - startAnimationMargin
    }

    private fun View.shouldStartHideAnimation(): Boolean {
        val viewPosition = getCenterY()
        return viewPosition < this@AnimatedRecyclerView.top + startAnimationMargin || viewPosition > this@AnimatedRecyclerView.bottom - startAnimationMargin
    }

    private fun View.getCenterY() =
        (top + bottom) / 2f
}
