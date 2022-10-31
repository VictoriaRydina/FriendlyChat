package com.example.friendlychat.core_ui.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <reified LD : LiveData<T>, reified T : Any> LifecycleOwner.observe(
    liveData: LD,
    noinline body: (T) -> Unit
) = liveData.observe(this, Observer(body))