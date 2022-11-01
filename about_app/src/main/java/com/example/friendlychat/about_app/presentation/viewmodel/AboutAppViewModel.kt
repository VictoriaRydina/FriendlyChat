package com.example.friendlychat.about_app.presentation.viewmodel

import android.text.Editable
import com.example.friendlychat.about_app.*
import com.example.friendlychat.about_app.presentation.exception.InvalidEmailException
import com.example.friendlychat.about_app.presentation.exception.InvalidMessageException
import com.example.friendlychat.about_app.presentation.validator.EmailValidator
import com.example.friendlychat.core_ui.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AboutAppViewModel
@Inject constructor(
    private val emailValidator: EmailValidator
) : BaseViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.IdleState)
    val state: StateFlow<ViewState> get() = _state

    fun setReport(
        userEmail: Editable?,
        message: Editable?,
        note: String
    ) {
        try {
            emailValidator.isEmailValid(userEmail)
            emailValidator.isMessageValid(message)
            _state.value = ViewState.ReportCreatedState(
                createReport(
                    userEmail = userEmail.toString(),
                    message = message.toString(),
                    note = note
                )
            )
        } catch (exception: InvalidEmailException) {
            _state.value = ViewState.ErrorState(exception.textResId)
        } catch (exception: InvalidMessageException) {
            _state.value = ViewState.ErrorState(exception.textResId)
        } catch (throwable: Throwable) {
            _state.value = ViewState.ErrorState(R.string.failed_to_create_message)
        }
    }

    fun setIdleState() {
        _state.value = ViewState.IdleState
    }

    private fun createReport(
        userEmail: String,
        message: String,
        note: String
    ) = "$message\n\n$note\n$userEmail"
}