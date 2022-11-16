package com.example.friendlychat.chat.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendlychat.chat.R
import com.example.friendlychat.chat.databinding.FragmentChatBinding
import com.example.friendlychat.chat.di.component.DaggerChatComponent
import com.example.friendlychat.chat.di.deps.ChatDeps
import com.example.friendlychat.chat.presentation.adapter.ContactUserAdapter
import com.example.friendlychat.chat.presentation.model.User
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ChatFragment : BaseFragment<FragmentChatBinding>(
    R.layout.fragment_chat,
    FragmentChatBinding::inflate
) {

    private var firebaseUser: FirebaseUser? = null
    private var referenceUsers: DatabaseReference? = null

    private var contactList = ArrayList<User>()
    private var contactUserAdapter = setUpContactUserAdapter()

    override fun initComponent() {
        DaggerChatComponent.factory()
            .create(requireActivity().application as ChatDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getContactsList()
    }

    private fun setUpContactUserAdapter(): ContactUserAdapter {
        return ContactUserAdapter {
            showToast("Navigate to this chat")
        }
    }

    private fun getContactsList() {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        referenceUsers = FirebaseDatabase.getInstance().getReference("Users")

        referenceUsers?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()

                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val contact = dataSnapshot.getValue(User::class.java)

                    if (contact!!.uid == firebaseUser?.uid) {
                        contactList.add(contact)
                    }
                    with(binding) {
                        usersListRecycler.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        usersListRecycler.adapter = contactUserAdapter
                        contactUserAdapter.submitList(contactList)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showToast(error.message)
            }
        })
    }
}