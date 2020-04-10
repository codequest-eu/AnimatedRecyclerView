package com.codequest.animatedrecyclerview

import android.view.View

internal fun View.getAbsoluteCenterY(): Int =
    (getAbsoluteTop() + getAbsoluteBottom()) / 2

internal fun View.getAbsoluteTop(): Int =
    if (isTopView()) top
    else top + (parent as View).getAbsoluteTop()

internal fun View.getAbsoluteBottom(): Int =
    if (isTopView()) bottom
    else bottom + (parent as View).getAbsoluteTop()

private fun View.isTopView() = parent == null
