package com.example.friendlychat.di.module

import dagger.Module

@Module(
    includes = [
        ViewModelFactoryModule::class
    ]
)
interface AppModule