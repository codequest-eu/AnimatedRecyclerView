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

    private val topMarginY: Float by lazy { startAnimationMargin }
    private val bottomMarginY: Float by lazy { height - startAnimationMargin }

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
            val itemView = it.itemView
            when {
                itemView.didEnterFromTop(dy) -> it.onEnterFromTop()
                itemView.didEnterFromBottom(dy) -> it.onEnterFromBottom()
                itemView.didExitToTop(dy) -> it.onExitToTop()
                itemView.didExitToBottom(dy) -> it.onExitToBottom()
            }
        }
    }

    private fun View.didEnterFromTop(dy: Int): Boolean =
        bottom >= topMarginY && bottom + dy < topMarginY

    private fun View.didEnterFromBottom(dy: Int): Boolean =
        top <= bottomMarginY && top + dy > bottomMarginY

    private fun View.didExitToTop(dy: Int): Boolean =
        bottom <= topMarginY && bottom + dy > topMarginY

    private fun View.didExitToBottom(dy: Int): Boolean =
        top >= bottomMarginY && top + dy < bottomMarginY
}
