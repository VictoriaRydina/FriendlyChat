package com.example.friendlychat.main_menu.fragment

import com.example.friendlychat.core_ui.navigation.InternalDeepLink
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.main_menu.R
import com.example.friendlychat.main_menu.databinding.FragmentMainMenuBinding
import com.example.friendlychat.main_menu.di.component.DaggerMainMenuComponent
import com.example.friendlychat.main_menu.di.deps.MainDeps
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainMenuFragment: BaseFragment<FragmentMainMenuBinding>(
    R.layout.fragment_main_menu,
    FragmentMainMenuBinding::inflate
) {

    override fun initComponent() {
        DaggerMainMenuComponent.factory()
            .create(requireActivity().application as MainDeps)
            .inject(this)
    }

    private lateinit var auth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null

    override fun setUi() {
        super.setUi()
        with(binding){
            mainMenuSignIn.setOnClickListener {
                navigateTo(InternalDeepLink.SIGN_IN)
            }
            mainMenuSignUp.setOnClickListener {
                navigateTo(InternalDeepLink.REGISTRATION)
            }
            mainMenuInfo.setOnClickListener {
                navigateTo(InternalDeepLink.INFO_ABOUT_APP)
            }
            mainMenuLanguageRu.setOnClickListener {
                showToast("Change language to Russian")
            }
            mainMenuLanguageEn.setOnClickListener {
                showToast("Change language to English")
            }
        }
        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser
        if (firebaseUser != null){
            navigateTo(InternalDeepLink.CHAT)
        }
    }
}