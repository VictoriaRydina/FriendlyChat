package com.example.friendlychat.core_ui.navigation

import androidx.annotation.StringRes
import com.example.friendlychat.core_ui.R

enum class InternalDeepLink(@StringRes val resId: Int) {
    CHAT(R.string.chat_deepLink),
    INFO_ABOUT_APP(R.string.info_about_app_deepLink),
    MAIN_MENU(R.string.main_menu_screen_deepLink),
    REGISTRATION(R.string.registration_deepLink),
    SIGN_IN(R.string.sign_in_deepLink)
}