package com.example.friendlychat.personal_area.di.component

import com.example.friendlychat.core_ui.di.ModuleScope
import com.example.friendlychat.personal_area.di.deps.PersonalAreaDeps
import com.example.friendlychat.personal_area.presentation.fragment.PersonalAreaFragment
import dagger.Component

@ModuleScope
@Component(
    dependencies = [PersonalAreaDeps::class]
)
interface PersonalAreaComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: PersonalAreaDeps): PersonalAreaComponent
    }

    fun inject(fragment: PersonalAreaFragment)
}