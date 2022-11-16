package com.example.friendlychat.di.deps

import com.example.friendlychat.about_app.di.AboutAppDeps
import com.example.friendlychat.chat.di.deps.ChatDeps
import com.example.friendlychat.main_menu.di.deps.MainDeps
import com.example.friendlychat.personal_area.di.deps.PersonalAreaDeps
import com.example.friendlychat.registration.di.RegistrationDeps
import com.example.friendlychat.sign_in.di.deps.SignInDeps

interface AppDeps :
    AboutAppDeps,
    ChatDeps,
    MainDeps,
    PersonalAreaDeps,
    RegistrationDeps,
    SignInDeps