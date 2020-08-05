package com.roshanaryal.quizkotlin/*
package com.roshanaryal.quizkotlin

import android.animation.Animator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView

class Anim {
    inner class Animation {
        private fun playAnim(
            view: View,
            value: Int,
            data: String
        ) {
            view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
                .setDuration(100).setStartDelay(100)
                .setInterpolator(DecelerateInterpolator())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        if (value == 0 && count < 4) {
                            var option = ""
                            if (count === 0) {
                                option = questionModalList.get(position).getOptionA()
                            } else if (count === 1) {
                                option = questionModalList.get(position).getOptionB()
                            } else if (count === 2) {
                                option = questionModalList.get(position).getOptionC()
                            } else if (count === 3) {
                                option = questionModalList.get(position).getOptionD()
                            }
                            playAnim(optionContainer.getChildAt(count), 0, option)
                            count++
                        }
                    }

                    override fun onAnimationEnd(animation: Animator) { //change data here and make view visibe
                        if (value == 0) {
                            try {
                                (view as TextView).text = data
                                val position
                                val position1: String =
                                    java.lang.String.valueOf(position + 1)
                                val noIndicator
                                noIndicator.setText(position1 + "/" + questionModalList.size())
                            } catch (e: Exception) {
                            }
                            view.tag = data
                            playAnim(view, 1, data)
                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}
                })
        }
    }
}*/
