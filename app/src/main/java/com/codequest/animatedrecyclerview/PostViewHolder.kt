package com.codequest.animatedrecyclerview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.view_post.view.*

class PostViewHolder(view: View) : AnimatedItemHolder(view) {
    private var animator: AnimatorSet? = null

    fun bind(post: Post) {
        itemView.apply {
            postTitle.text = post.title
            postBody.text = post.body
            postUserEmail.text = post.userEmail

            val circularProgressDrawable =
                CircularProgressDrawable(itemView.context).apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                    start()
                }

            Glide
                .with(itemView)
                .load(getAvatarUrl(post))
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(circularProgressDrawable)
                .timeout(5000)
                .into(postUserAvatar)
        }
    }

    private fun getAvatarUrl(post: Post) =
        "https://api.adorable.io/avatars/200/${post.userEmail}.png"

    override fun onEnterFromTop() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToTop() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = 0f,
            finalTranslationValue = -ANIMATED_TRANSLATION_AMOUNT,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 0f
        )
    }

    override fun onEnterFromBottom() {
        animator?.cancel()
        animateTranslation(
            startTranslationValue = ANIMATED_TRANSLATION_AMOUNT,
            finalTranslationValue = 0f,
            startAlphaValue = itemView.alpha,
            finalAlphaValue = 1f
        )
    }

    override fun onExitToBottom() {
        animator?.cancel()
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
                .ofFloat(itemView, "translationY", startTranslationValue, finalTranslationValue)

        val alphaAnimator =
            ObjectAnimator
                .ofFloat(itemView, "alpha", startAlphaValue, finalAlphaValue)

        animator = AnimatorSet().apply {
            playTogether(translationAnimator, alphaAnimator)
            duration = ANIMATION_DURATION
            start()
        }
    }

    private companion object {
        const val ANIMATION_DURATION = 150L
        const val ANIMATED_TRANSLATION_AMOUNT = 200f
    }
}
