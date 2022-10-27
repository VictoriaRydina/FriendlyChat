package com.example.friendlychat.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.friendlychat.core_ui.di.ApplicationScope
import com.example.friendlychat.core_ui.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
    ]
)
interface ViewModelFactoryModule {

    @ApplicationScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}