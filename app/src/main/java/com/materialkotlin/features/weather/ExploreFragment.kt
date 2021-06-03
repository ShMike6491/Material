package com.materialkotlin.features.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.materialkotlin.R
import com.materialkotlin.databinding.FragmentExploreBinding

class ExploreFragment : Fragment(R.layout.fragment_explore) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentExploreBinding.bind(view)

        binding.apply {
            viewPager.adapter = WeatherPagerAdapter(this@ExploreFragment)

            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.setIcon(getTabIcon(position))
                tab.text = getTabTitle(position)
            }.attach()
        }
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            EARTH_PAGE_INDEX -> R.drawable.ic_earth
            MARS_PAGE_INDEX -> R.drawable.ic_mars
            SPACE_PAGE_INDEX -> R.drawable.ic_system
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            EARTH_PAGE_INDEX -> getString(R.string.earth)
            MARS_PAGE_INDEX -> getString(R.string.mars)
            SPACE_PAGE_INDEX -> getString(R.string.system)
            else -> null
        }
    }
}