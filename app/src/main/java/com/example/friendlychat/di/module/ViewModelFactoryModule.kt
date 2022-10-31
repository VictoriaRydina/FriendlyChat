package com.example.friendlychat.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.friendlychat.about_app.di.AboutAppViewModelModule
import com.example.friendlychat.core_ui.di.ApplicationScope
import com.example.friendlychat.core_ui.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        AboutAppViewModelModule::class
    ]
)
interface ViewModelFactoryModule {

    @ApplicationScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}