package com.example.friendlychat.registration.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.registration.R
import com.example.friendlychat.registration.databinding.FragmentRegistrationBinding
import com.example.friendlychat.registration.di.DaggerRegistrationComponent
import com.example.friendlychat.registration.di.RegistrationDeps
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(
    R.layout.fragment_registration,
    FragmentRegistrationBinding::inflate
) {

    private lateinit var auth: FirebaseAuth
    private lateinit var referenceUsers: DatabaseReference
    private var firebaseUserId: String? = null

    override fun initComponent() {
        DaggerRegistrationComponent.factory()
            .create(requireActivity().application as RegistrationDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        with(binding) {
            registrationButton.setOnClickListener {
                registerUser()
            }
            registrationBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
            registrationWithGoogleButton.setOnClickListener {
                showToast("Register with Google")
            }
        }
    }

    private fun registerUser() {
        with(binding) {
            val userName = registrationUsername.text.toString()
            val userEmail = registrationEmail.text.toString()
            val userPassword = registrationPassword.text.toString()

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                showToast(R.string.fill_in_all_the_fields)
            } else {
                auth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            firebaseUserId = auth.currentUser?.uid
                            referenceUsers = FirebaseDatabase.getInstance().reference.child("Users")
                                .child(firebaseUserId.toString())
                            val userHashMap = HashMap<String, Any>()
                            userHashMap["uid"] = firebaseUserId.toString()
                            userHashMap["userName"] = userName
                            userHashMap["profile"] =
                                "https://firebasestorage.googleapis.com/v0/b/friendly-chat-96056.appspot.com/o/2009.i305.017..party%20set-08.jpg?alt=media&token=88f59325-e266-468c-8ead-47d9ebcb6715"
                            userHashMap["cover"] =
                                "https://firebasestorage.googleapis.com/v0/b/friendly-chat-96056.appspot.com/o/linear-smooth-flow-lines-background.jpg?alt=media&token=8eec3249-ac17-4cd0-b1f7-445df4a1f73b"
                            userHashMap["status"] = "offline"
                            userHashMap["search"] = userName.lowercase(Locale.getDefault())
                            referenceUsers.updateChildren(userHashMap)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        showToast("Navigate to chat")
                                    }
                                }
                        } else {
                            showToast("Error: " + it.exception?.message.toString())
                        }
                    }
            }
        }
    }
}