package com.example.friendlychat.about_app

import android.text.Editable
import androidx.core.util.PatternsCompat
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