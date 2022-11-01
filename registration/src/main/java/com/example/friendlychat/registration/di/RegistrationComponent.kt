package com.example.friendlychat.registration.di

import com.example.friendlychat.core_ui.di.ModuleScope
import com.example.friendlychat.registration.presentation.RegistrationFragment
import dagger.Component

@ModuleScope
@Component(
    dependencies = [RegistrationDeps::class]
)
interface RegistrationComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: RegistrationDeps): RegistrationComponent
    }

    fun inject(fragment: RegistrationFragment)
}