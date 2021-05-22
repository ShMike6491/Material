package com.materialkotlin.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.materialkotlin.R
import com.materialkotlin.databinding.FragmentHomeBinding
import com.materialkotlin.features.dialogs.DescriptionBottomDialog
import com.materialkotlin.util.AppState

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var title = ""
    private var description = ""
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.fabPictureDescription.setOnClickListener {
            val dialog = DescriptionBottomDialog.newInstance(title, description)
            dialog.show(childFragmentManager, "add_dialog_fragment")
        }

        val viewModel: HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { render(it) })
        viewModel.requestDailyImage()
    }

    private fun render(state: AppState?) {
        when (state) {
            is AppState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                binding.fabPictureDescription.visibility = View.VISIBLE
                binding.mainToolbar.title = state.data.title
                val url = state.data.url
                url?.let {
                    binding.ivDailyPicture.load(url) {
                        lifecycle(this@HomeFragment)
                        error(R.drawable.default_background)
                        placeholder(R.drawable.default_background)
                    }
                }
                title = state.data.title ?: "No title"
                description = state.data.description ?: "No description"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}