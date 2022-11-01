package com.example.friendlychat.about_app.di

import androidx.lifecycle.ViewModel
import com.example.friendlychat.about_app.presentation.viewmodel.AboutAppViewModel
import com.example.friendlychat.core_ui.presentation.viewmodel.VMKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AboutAppViewModelModule {

    @[Binds IntoMap VMKey(AboutAppViewModel::class)]
    fun bindAboutAppViewModel(viewModel: AboutAppViewModel): ViewModel
}