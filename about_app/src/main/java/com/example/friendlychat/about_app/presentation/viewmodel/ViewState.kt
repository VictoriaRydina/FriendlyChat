package com.example.friendlychat.about_app.presentation.viewmodel

sealed class ViewState {
    data class ReportCreatedState(val data: String) : ViewState()
    data class ErrorState(val data: Int) : ViewState()
    object IdleState : ViewState()
}