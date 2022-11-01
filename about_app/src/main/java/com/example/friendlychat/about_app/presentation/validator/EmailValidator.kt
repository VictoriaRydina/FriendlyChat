package com.example.friendlychat.about_app.presentation.validator

import android.text.Editable
import androidx.core.util.PatternsCompat
import com.example.friendlychat.about_app.presentation.exception.InvalidEmailException
import com.example.friendlychat.about_app.presentation.exception.InvalidMessageException
import javax.inject.Inject

class EmailValidator
@Inject constructor() {

    fun isEmailValid(email: Editable?) {
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
            throw InvalidEmailException()
        }
    }

    fun isMessageValid(message: Editable?) {
        if (message.isNullOrBlank()) throw InvalidMessageException()
    }
}