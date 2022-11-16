package com.example.friendlychat.chat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.friendlychat.chat.databinding.ItemChatWithContactBinding
import com.example.friendlychat.core_ui.presentation.adapter.BaseItemCallback
import com.example.friendlychat.core_ui.presentation.model.User
import com.squareup.picasso.Picasso

class ContactUserAdapter(private val onItemClickListener: (User) -> Unit) :
    ListAdapter<User, ContactUserAdapter.UserContactItemViewHolder>(
        BaseItemCallback<User>()
    ) {

    class UserContactItemViewHolder(private val binding: ItemChatWithContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: User,
            onItemClickListener: (User) -> Unit
        ) {
            binding.apply {
                Picasso.get().load(item.profile)
                    .placeholder(com.example.friendlychat.core_ui.R.drawable.ic_person)
                    .into(contactImage)
                contactPersonName.text = item.userName
                root.setOnClickListener { onItemClickListener(item) }
            }
        }
    }

    override fun onBindViewHolder(holder: UserContactItemViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserContactItemViewHolder {
        val binding = ItemChatWithContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserContactItemViewHolder(binding)
    }
}