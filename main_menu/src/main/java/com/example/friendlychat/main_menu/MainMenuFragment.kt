package com.example.friendlychat.main_menu

import com.example.friendlychat.core_ui.presentation.fragment.BaseFragment
import com.example.friendlychat.core_ui.utils.showToast
import com.example.friendlychat.main_menu.databinding.FragmentMainMenuBinding

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
        }
    }
}