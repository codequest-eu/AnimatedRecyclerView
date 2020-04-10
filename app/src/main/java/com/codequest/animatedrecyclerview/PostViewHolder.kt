package com.codequest.animatedrecyclerview

import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.view_post.view.*

class PostViewHolder(view: View) : AnimatedItemHolder(view) {

    fun bind(post: Post) {
        itemView.apply {
            postTitle.text = post.title
            postBody.text = post.body
        }
    }

    override fun startShowAnimation() {
        itemView.startAnimation(AlphaAnimation(itemView.alpha, 1f).apply {
            duration = 100L
            fillAfter = true
        })
    }

    override fun startHideAnimation() {
        itemView.startAnimation(AlphaAnimation(itemView.alpha, 0f).apply {
            duration = 100L
            fillAfter = true
        })
    }
}
