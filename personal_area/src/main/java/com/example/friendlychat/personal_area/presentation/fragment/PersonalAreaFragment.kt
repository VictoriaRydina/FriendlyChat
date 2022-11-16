package com.example.friendlychat.personal_area.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.presentation.model.User
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.personal_area.R
import com.example.friendlychat.personal_area.databinding.FragmentPersonalAreaBinding
import com.example.friendlychat.personal_area.di.component.DaggerPersonalAreaComponent
import com.example.friendlychat.personal_area.di.deps.PersonalAreaDeps
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class PersonalAreaFragment : BaseFragment<FragmentPersonalAreaBinding>(
    R.layout.fragment_personal_area,
    FragmentPersonalAreaBinding::inflate
) {

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var referenceUsers: DatabaseReference

    override fun initComponent() {
        DaggerPersonalAreaComponent.factory()
            .create(requireActivity().application as PersonalAreaDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            personalAreaBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
            firebaseUser = FirebaseAuth.getInstance().currentUser!!
            referenceUsers =
                FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)

            referenceUsers.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    userName.text = user?.userName
                    Picasso.get().load(user?.cover).into(coverImage)
                    Picasso.get().load(user?.profile).into(userImage)
                }

                override fun onCancelled(error: DatabaseError) {
                    showToast(error.message)
                }
            })
        }
    }
}
