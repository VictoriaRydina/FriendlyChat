package com.example.friendlychat.chat.di.component

import com.example.friendlychat.chat.di.deps.ChatDeps
import com.example.friendlychat.chat.presentation.ActiveChatFragment
import com.example.friendlychat.chat.presentation.ChatFragment
import com.example.friendlychat.core_ui.di.ModuleScope
import dagger.Component

@ModuleScope
@Component(
    dependencies = [ChatDeps::class]
)
interface ChatComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: ChatDeps): ChatComponent
    }

    fun inject(fragment: ChatFragment)

    fun inject(fragment: ActiveChatFragment)
}