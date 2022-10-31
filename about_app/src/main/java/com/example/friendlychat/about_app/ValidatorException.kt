package com.example.friendlychat.about_app

import androidx.annotation.StringRes

sealed class ValidatorException: Throwable()

class InvalidEmailException(@StringRes val textResId: Int = R.string.invalid_email) :
    ValidatorException()

class InvalidMessageException(@StringRes val textResId: Int = R.string.invalid_message) :
    ValidatorException()