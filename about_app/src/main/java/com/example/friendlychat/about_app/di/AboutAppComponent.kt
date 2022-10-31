package com.example.friendlychat.about_app.di

import com.example.friendlychat.about_app.fragments.AboutAppFragment
import com.example.friendlychat.about_app.fragments.FeedbackFragment
import com.example.friendlychat.core_ui.di.ModuleScope
import dagger.Component

@ModuleScope
@Component(
    dependencies = [AboutAppDeps::class]
)
interface AboutAppComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: AboutAppDeps): AboutAppComponent
    }

    fun inject(fragment: AboutAppFragment)

    fun inject(fragment: FeedbackFragment)
}