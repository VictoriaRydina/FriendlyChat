package com.example.friendlychat.chat.presentation

import android.os.Bundle
import android.view.View
import com.example.friendlychat.chat.R
import com.example.friendlychat.chat.databinding.FragmentActiveChatBinding
import com.example.friendlychat.chat.di.component.DaggerChatComponent
import com.example.friendlychat.chat.di.deps.ChatDeps
import com.example.friendlychat.chat.presentation.ChatFragment.Companion.USER_ID
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.presentation.model.User
import com.example.friendlychat.core_ui.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class ActiveChatFragment : BaseFragment<FragmentActiveChatBinding>(
    R.layout.fragment_active_chat,
    FragmentActiveChatBinding::inflate
) {

    private var firebaseUser: FirebaseUser? = null
    private var reference: DatabaseReference? = null

    override fun initComponent() {
        DaggerChatComponent.factory()
            .create(requireActivity().application as ChatDeps)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = arguments?.get(USER_ID)

        firebaseUser = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId.toString())

        reference!!.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                with(binding) {
                    val user = snapshot.getValue(User::class.java)
                    activePersonName.text = user?.userName
                    if (user!!.profile == "") {
                        activeUserImage.setImageResource(com.example.friendlychat.core_ui.R.drawable.ic_person)
                    } else {
                        Picasso.get().load(user.profile).into(activeUserImage)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        with(binding) {
            sendMessageButton.setOnClickListener {
                val message = editMessage.text.toString()
                if (message.isEmpty()) {
                    showToast(R.string.enter_your_message)
                } else {
                    sendMessage(firebaseUser!!.uid, userId.toString(), message)
                }
            }

        }
    }

    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val hashMap: HashMap<String, String> = HashMap()
        hashMap["senderId"] = senderId
        hashMap["receiverId"] = receiverId
        hashMap["message"] = message

        reference.child("Chat").push().setValue(hashMap)
    }
}