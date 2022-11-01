package com.example.friendlychat.about_app.presentation.fragments

import androidx.navigation.fragment.findNavController
import com.example.friendlychat.about_app.R
import com.example.friendlychat.about_app.databinding.FragmentAboutAppBinding
import com.example.friendlychat.about_app.di.AboutAppDeps
import com.example.friendlychat.about_app.di.DaggerAboutAppComponent
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment

class AboutAppFragment : BaseFragment<FragmentAboutAppBinding>(
    R.layout.fragment_about_app,
    FragmentAboutAppBinding::inflate
) {

    override fun initComponent() {
        DaggerAboutAppComponent.factory()
            .create(requireActivity().application as AboutAppDeps)
            .inject(this)
    }

    override fun setUi() {
        super.setUi()
        with(binding) {
            feedbackButton.setOnClickListener {
                findNavController().navigate(R.id.feedbackFragment)
            }
            privacyPolicyButton.setOnClickListener {
                findNavController().navigate(R.id.privacyPolicyFragment)
            }
            termsOfUseButton.setOnClickListener {
                findNavController().navigate(R.id.termsOfUseFragment)
            }
            aboutAppButtonBack.setOnClickListener { findNavController().navigateUp() }
        }
    }
}