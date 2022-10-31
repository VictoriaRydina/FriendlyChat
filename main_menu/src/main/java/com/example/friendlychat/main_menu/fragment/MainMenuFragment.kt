package com.example.friendlychat.main_menu.fragment

import com.example.friendlychat.core_ui.navigation.InternalDeepLink
import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.main_menu.R
import com.example.friendlychat.main_menu.databinding.FragmentMainMenuBinding
import com.example.friendlychat.main_menu.di.component.DaggerMainMenuComponent
import com.example.friendlychat.main_menu.di.deps.MainDeps

class MainMenuFragment: BaseFragment<FragmentMainMenuBinding>(
    R.layout.fragment_main_menu,
    FragmentMainMenuBinding::inflate
) {

    override fun setUi() {
        super.setUi()
        with(binding){
            mainMenuSignIn.setOnClickListener {
                showToast(R.string.navigate_to_sign_in)
            }
            mainMenuSignUp.setOnClickListener {
                showToast("Navigate to SignUp")
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
    }

    override fun initComponent() {
        DaggerMainMenuComponent.factory()
            .create(requireActivity().application as MainDeps)
            .inject(this)
    }
}