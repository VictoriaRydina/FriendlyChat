package com.example.friendlychat.chat.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendlychat.chat.R
import com.example.friendlychat.chat.databinding.FragmentChatBinding
import com.example.friendlychat.chat.di.component.DaggerChatComponent
import com.example.friendlychat.chat.di.deps.ChatDeps
import com.example.friendlychat.chat.presentation.adapter.ContactUserAdapter
import com.example.friendlychat.core_ui.navigation.InternalDeepLink
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.presentation.model.User
import com.example.friendlychat.core_ui.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ChatFragment : BaseFragment<FragmentChatBinding>(
    R.layout.fragment_chat,
    FragmentChatBinding::inflate
) {

    private var contactList = ArrayList<User>()

    override fun initComponent() {
        DaggerChatComponent.factory()
            .create(requireActivity().application as ChatDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getContactsList()
        binding.userImage.setOnClickListener {
            navigateTo(InternalDeepLink.PERSONAL_AREA)
        }
    }

    private fun getContactsList() {
        val firebaseUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val referenceUsers = FirebaseDatabase.getInstance().getReference("Users")

        referenceUsers.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                for (dataSnapshot: DataSnapshot in snapshot.children) {
                    val contact = dataSnapshot.getValue(User::class.java)
                    if (!contact!!.uid.equals(firebaseUser.uid)) {
                        contactList.add(contact)
                    }
                    fun setUpContactUserAdapter(): ContactUserAdapter {
                        return ContactUserAdapter {
                            navigateTo(
                                R.id.action_chatFragment_to_activeChatFragment,
                                bundleOf(USER_ID to contact.uid)
                            )
                        }
                    }
                    val contactUserAdapter = setUpContactUserAdapter()
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

    companion object {
        const val USER_ID = "userId"
    }
}