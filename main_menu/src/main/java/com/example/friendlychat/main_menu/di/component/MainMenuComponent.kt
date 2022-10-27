package com.example.friendlychat.main_menu.di.component

import com.example.friendlychat.core_ui.di.ModuleScope
import com.example.friendlychat.main_menu.MainMenuFragment
import com.example.friendlychat.main_menu.di.deps.MainDeps
import dagger.Component

@ModuleScope
@Component(
    dependencies = [MainDeps::class]
)
interface MainMenuComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: MainDeps): MainMenuComponent
    }

    fun inject(fragment: MainMenuFragment)
}