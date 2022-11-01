package com.example.friendlychat.about_app.presentation.exception

import androidx.annotation.StringRes
import com.example.friendlychat.about_app.R

sealed class ValidatorException: Throwable()

class InvalidEmailException(@StringRes val textResId: Int = R.string.invalid_email) :
    ValidatorException()

class InvalidMessageException(@StringRes val textResId: Int = R.string.invalid_message) :
    ValidatorException()