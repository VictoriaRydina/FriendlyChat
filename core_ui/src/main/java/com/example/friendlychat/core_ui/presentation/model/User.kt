package com.example.friendlychat.core_ui.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String? = null,
    val userName: String? = null,
    val profile: String? = null,
    val cover: String? = null,
    val status: String? = null,
    val search: String? = null,
): Parcelable