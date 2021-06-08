package com.materialkotlin.features.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.materialkotlin.R
import com.materialkotlin.data.remote.NasaResponse
import com.materialkotlin.databinding.FragmentHomeBinding
import com.materialkotlin.features.dialogs.DescriptionBottomDialog

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var title = ""
    private var description = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.fabPictureDescription.setOnClickListener {
            val dialog = DescriptionBottomDialog.newInstance(title, description)
            dialog.show(childFragmentManager, "add_dialog_fragment")
        }

        viewModel.getLiveData().observe(viewLifecycleOwner, { applyChanges(it) })
        viewModel.requestDailyImage()
    }

    private fun applyChanges(data: NasaResponse) {
        binding.apply {
            fabPictureDescription.visibility = View.VISIBLE
            data.title?.let {
                mainToolbar.title = it
                title = it
            }
            data.description?.let {
                description = it
            }
            data.url?.let {
                ivDailyPicture.load(it) {
                    lifecycle(this@HomeFragment)
                    error(R.drawable.default_background)
                    placeholder(R.drawable.default_background)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}