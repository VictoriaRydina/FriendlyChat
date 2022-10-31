package com.example.friendlychat

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.friendlychat.di.component.AppComponent
import com.example.friendlychat.di.component.DaggerAppComponent
import com.example.friendlychat.di.deps.AppDeps

class App: Application(), AppDeps {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }

    override val aboutAppViewModelFactory: ViewModelProvider.Factory
        get() = appComponent.viewModelFactory()
}