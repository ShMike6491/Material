package com.materialkotlin.features.explore

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.materialkotlin.R
import kotlinx.android.synthetic.main.fragment_mars.*

class MarsFragment : Fragment(R.layout.fragment_mars) {
    private var isExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initState()
        configFAB()
    }

    private fun initState() {
        transparent_background.apply {
            alpha = 0f
        }
        option_two.apply {
            alpha = 0f
            isClickable = false
        }
        option_one.apply {
            alpha = 0f
            isClickable = false
        }
    }

    private fun configFAB() = fab.setOnClickListener {
        if (isExpanded) animateScene(false) else animateScene(true)
    }

    private fun animateScene(param: Boolean) {
        isExpanded = param

        if (param) {
            expendFab(225f, -130f, -250f)
            extendOptions(option_one, 1f)
            extendOptions(option_two, 1f)
            presentBackground(0.9f)
        } else {
            expendFab(-180f, 0f, 0f)
            extendOptions(option_one,0f)
            extendOptions(option_two,0f)
            presentBackground(0f)
        }
    }

    private fun expendFab(rotation: Float, optionOne: Float, optionTwo: Float) {
        ObjectAnimator.ofFloat(fab, "rotation", 0f, rotation).start()
        ObjectAnimator.ofFloat(option_two, "translationY", optionOne).start()
        ObjectAnimator.ofFloat(option_one, "translationY", optionTwo).start()
    }

    private fun extendOptions(view: TextView, alpha: Float) {
        view.animate()
            .alpha(alpha)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.isClickable = isExpanded
                    view.setOnClickListener {
                        Toast.makeText(requireActivity(), "Option clicked", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun presentBackground( alpha: Float) {
        transparent_background.animate()
            .alpha(alpha)
            .setDuration(300)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    transparent_background.isClickable = isExpanded
                }
            })
    }
}