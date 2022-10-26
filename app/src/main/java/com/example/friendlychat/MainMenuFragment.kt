package com.example.friendlychat

import com.example.friendlychat.databinding.FragmentMainMenuBinding
import com.example.friendlychat.utils.showToast

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