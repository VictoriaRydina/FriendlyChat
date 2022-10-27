package com.example.friendlychat.di.component

import android.content.Context
import com.example.friendlychat.presentation.MainActivity
import com.example.friendlychat.core_ui.di.ApplicationScope
import com.example.friendlychat.di.module.AppModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        AppModule::class
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}