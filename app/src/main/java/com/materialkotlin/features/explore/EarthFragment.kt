package com.materialkotlin.features.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.materialkotlin.R
import com.materialkotlin.databinding.FragmentEarthBinding

class EarthFragment : Fragment(R.layout.fragment_earth) {
    private val viewModel: ExploreViewModel by viewModels()

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
    }
}