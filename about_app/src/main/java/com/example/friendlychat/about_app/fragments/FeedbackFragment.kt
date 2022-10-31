package com.example.friendlychat.about_app.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.friendlychat.about_app.AboutAppViewModel
import com.example.friendlychat.about_app.R
import com.example.friendlychat.about_app.ViewState
import com.example.friendlychat.about_app.databinding.FragmentFeedbackBinding
import com.example.friendlychat.about_app.di.AboutAppDeps
import com.example.friendlychat.about_app.di.DaggerAboutAppComponent
import com.example.friendlychat.core_ui.presentation.fragment.BaseViewModelFragment
import com.example.friendlychat.core_ui.utils.invisible
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.core_ui.utils.visible

class FeedbackFragment : BaseViewModelFragment<FragmentFeedbackBinding, AboutAppViewModel>(
    R.layout.fragment_feedback,
    FragmentFeedbackBinding::inflate,
    AboutAppViewModel::class.java
) {

    override fun initComponent() {
        DaggerAboutAppComponent.factory()
            .create(requireActivity().application as AboutAppDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setIdleState()
        with(binding) {
            enterEmail.doAfterTextChanged { editable ->
                if (editable.isNullOrEmpty()) {
                    emailHintText.invisible()
                } else {
                    emailHintText.visible()
                }
            }
            buttonSend.setOnClickListener { setReport() }
            feedbackButtonBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun setObservers() {
        lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: Any) {
        when (state) {
            is ViewState.ReportCreatedState -> {
                sendReport(state.data)
            }
            is ViewState.ErrorState -> {
                showToast(getString(state.data))
            }
        }
    }

    private fun setReport() {
        viewModel.setReport(
            userEmail = binding.enterEmail.text,
            message = binding.errorInputBar.text,
            note = getString(R.string.email_for_feedback)
        )
    }

    private fun sendReport(report: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(SUPPORT_EMAIL))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback))
            putExtra(Intent.EXTRA_TEXT, report)
        }
        startActivity(
            Intent.createChooser(
                intent,
                getString(R.string.er_choose_an_app_to_email)
            )
        )
    }

    companion object {
        private const val SUPPORT_EMAIL = "friendlychat@gmail.com"
    }
}