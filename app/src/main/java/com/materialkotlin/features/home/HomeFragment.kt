package com.materialkotlin.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.materialkotlin.R
import com.materialkotlin.databinding.FragmentHomeBinding
import com.materialkotlin.util.AppState

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val viewModel: HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, {render(it)})
        viewModel.requestDailyImage()
    }

    private fun render(state: AppState?) {
        when(state) {
            is AppState.Loading -> {
                binding.pbLoading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.pbLoading.visibility = View.GONE
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvDailyPicTitle.text = state.data.title
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}