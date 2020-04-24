package com.codequest.animatedrecyclerview

import android.animation.ValueAnimator
import android.view.View
import kotlinx.android.synthetic.main.view_post.view.*

class PostViewHolder(view: View) : AnimatedItemHolder(view) {
    private var animator: ValueAnimator? = null
    private var isShown = false

    fun bind(post: Post) {
        itemView.apply {
            postTitle.text = post.title
            postBody.text = post.body

            alpha = 0f
            this@PostViewHolder.isShown = false
        }
    }

    override fun startShowAnimation() {
        if (!isShown) {
            isShown = true
            animator?.cancel()
            animator = ValueAnimator.ofFloat(itemView.alpha, 1f).apply {
                addUpdateListener {
                    itemView.alpha = it.animatedValue as Float
                }
                start()
            }
        }
    }

    override fun startHideAnimation() {
        if (isShown) {
            isShown = false
            animator?.cancel()
            animator = ValueAnimator.ofFloat(itemView.alpha, 0f).apply {
                addUpdateListener {
                    itemView.alpha = it.animatedValue as Float
                }
                start()
            }
        }
    }
}
