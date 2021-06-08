package com.materialkotlin.features.home

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
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

        renderText()
    }

    private fun renderText() {
        val spannable = SpannableString("My text \nbullet one\nbullet two")

        fun rnd(): Int = (0..255).random()
        val color = Color.argb(255, rnd(), rnd(), rnd())

        spannable.setSpan(
            BulletSpan(40, color),
            /* начало элемента списка */ 9, /* конец элемента списка */ 18,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(
            BulletSpan(40, color),
            /* начало элемента списка */ 20, /* конец элемента списка */ spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(
            ForegroundColorSpan(color),
            0, 7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannable.setSpan(
            StyleSpan(BOLD),
            /* начало элемента списка */ 1, /* конец элемента списка */ spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvSpan.text = spannable

/*
        // передаём span
        textView.setText(spannableString, BufferType.SPANNABLE)
        // получаем текст и кастим его в span, потому что ранее передали туда span
        val spannableText = textView.text as Spannable
*/
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