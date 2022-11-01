package com.example.friendlychat.about_app.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.friendlychat.about_app.R
import com.example.friendlychat.about_app.databinding.FragmentTermsOfUseBinding

class TermsOfUseFragment: Fragment(R.layout.fragment_terms_of_use) {

    private lateinit var binding: FragmentTermsOfUseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTermsOfUseBinding.inflate(inflater, container, false)
        binding.termsOfUseButton.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}