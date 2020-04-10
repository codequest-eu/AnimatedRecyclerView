package com.codequest.animatedrecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AnimatedItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun startShowAnimation()
    abstract fun startHideAnimation()
}
