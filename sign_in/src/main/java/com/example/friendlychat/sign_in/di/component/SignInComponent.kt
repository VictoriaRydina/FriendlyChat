package com.example.friendlychat.sign_in.di.component

import com.example.friendlychat.core_ui.di.ModuleScope
import com.example.friendlychat.sign_in.presentation.SignInFragment
import com.example.friendlychat.sign_in.di.deps.SignInDeps
import dagger.Component

@ModuleScope
@Component(
    dependencies = [SignInDeps::class]
)
interface SignInComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: SignInDeps): SignInComponent
    }

    fun inject(fragment: SignInFragment)
}