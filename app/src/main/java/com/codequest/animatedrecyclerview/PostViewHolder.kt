package com.codequest.animatedrecyclerview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
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

    override fun onEnterFromTop() {
        animateTranslation(
            startTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToTop() {
        animateTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    override fun onEnterFromBottom() {
        animateTranslation(
            startTranslationValue = ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToBottom() {
        animateTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    private fun animateTranslation(
        startTranslationValue: Float,
        finalTranslationValue: Float,
        startAlphaValue: Float,
        finalAlphaValue: Float
    ) {
        val translationAnimator =
            ObjectAnimator
                .ofFloat(itemView, "translationX", startTranslationValue, finalTranslationValue)

        val alphaAnimator =
            ObjectAnimator
                .ofFloat(itemView, "alpha", startAlphaValue, finalAlphaValue)

        AnimatorSet().apply {
            playTogether(translationAnimator, alphaAnimator)
            duration = ANIMATION_DURATION
            start()
        }
    }

    private companion object {
        const val ANIMATION_DURATION = 150L
        const val ANIMATED_TRANSLATION_AMOUNT = 400f
    }
}
