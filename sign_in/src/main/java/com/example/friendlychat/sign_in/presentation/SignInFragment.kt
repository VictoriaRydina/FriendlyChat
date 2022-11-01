package com.example.friendlychat.sign_in.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.sign_in.R
import com.example.friendlychat.sign_in.databinding.FragmentSignInBinding
import com.example.friendlychat.sign_in.di.component.DaggerSignInComponent
import com.example.friendlychat.sign_in.di.deps.SignInDeps
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    R.layout.fragment_sign_in,
    FragmentSignInBinding::inflate
) {

    private lateinit var auth: FirebaseAuth

    override fun initComponent() {
        DaggerSignInComponent.factory()
            .create(requireActivity().application as SignInDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        with(binding) {
            signInBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
            signInButton.setOnClickListener {
                loginUser()
            }
        }
    }

    private fun loginUser() {
        with(binding) {
            val userEmail = signInEmail.text.toString()
            val userPassword = signInPassword.text.toString()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                showToast(com.example.friendlychat.core_ui.R.string.fill_in_all_the_fields)
            } else {
                auth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Navigate to chat")
                        } else {
                            showToast("Error: " + it.exception?.message.toString())
                        }
                    }
            }
        }
    }
}