package com.materialkotlin.features.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.materialkotlin.R
import kotlinx.android.synthetic.main.dialog_description.*

class DescriptionBottomDialog : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = arguments?.getString(DIALOG_TITLE_TAG).toString()
        tv_description.text = arguments?.getString(DIALOG_DESCRIPTION_TAG).toString()
    }

    companion object {
        fun newInstance(title: String, description: String): DescriptionBottomDialog {
            val fragment = DescriptionBottomDialog()
            val args = Bundle()
            args.putString(DIALOG_TITLE_TAG, title)
            args.putString(DIALOG_DESCRIPTION_TAG, description)
            fragment.arguments = args
            return fragment
        }
    }
}

private const val DIALOG_TITLE_TAG = "com.materialkotlin.features.dialogs.title"
private const val DIALOG_DESCRIPTION_TAG = "com.materialkotlin.features.dialogs.desc"
