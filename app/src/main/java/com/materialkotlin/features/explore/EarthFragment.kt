package com.materialkotlin.features.explore

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.api.load
import com.materialkotlin.R
import com.materialkotlin.databinding.FragmentEarthBinding
import kotlinx.android.synthetic.main.fragment_earth.*

class EarthFragment : Fragment(R.layout.fragment_earth) {
    private val viewModel: ExploreViewModel by viewModels()
    private var isExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEarthBinding.bind(view)

        viewModel.epicList.observe(viewLifecycleOwner) {
            binding.ivEarth.load(it) {
                lifecycle(this@EarthFragment)
                error(R.drawable.bg_earth)
                placeholder(R.drawable.bg_earth)
            }
        }

        binding.ivEarth.setOnClickListener {
            handleScaleAnimation()
        }
    }

    private fun handleScaleAnimation() {
        isExpanded = !isExpanded
        TransitionManager.beginDelayedTransition(
            fl_image_holder, TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())
        )

        val params: ViewGroup.LayoutParams = iv_Earth.layoutParams
        params.height =
            if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
        iv_Earth.layoutParams = params
        iv_Earth.scaleType =
            if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
    }
}