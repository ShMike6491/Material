package com.materialkotlin.features.explore

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val EARTH_PAGE_INDEX = 0
const val MARS_PAGE_INDEX = 1
const val SPACE_PAGE_INDEX = 2

class WeatherPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val tabFragments: Map<Int, () -> Fragment> = mapOf(
        EARTH_PAGE_INDEX to { EarthFragment() },
        MARS_PAGE_INDEX to { MarsFragment() },
        SPACE_PAGE_INDEX to { SpaceFragment() }
    )

    override fun getItemCount() = tabFragments.size

    override fun createFragment(position: Int): Fragment {
        return tabFragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}