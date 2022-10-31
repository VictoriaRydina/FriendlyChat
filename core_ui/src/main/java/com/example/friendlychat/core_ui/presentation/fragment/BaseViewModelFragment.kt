package com.example.friendlychat.core_ui.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.friendlychat.core_ui.presentation.viewmodel.BaseViewModel
import com.example.friendlychat.core_ui.utils.observe
import com.example.friendlychat.core_ui.utils.showToast
import javax.inject.Inject

abstract class BaseViewModelFragment<VB : ViewBinding, VM : BaseViewModel>(
    @LayoutRes layoutRes: Int,
    bindingInflater: (inflater: LayoutInflater) -> VB,
    private val viewModelClass: Class<VM>
) : BaseFragment<VB>(layoutRes, bindingInflater) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(requireActivity().viewModelStore, viewModelFactory).get(viewModelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    protected open fun setObservers() {
        observe(viewModel.toastLD) { showToast(it) }
        observe(viewModel.toastResLD) { showToast(it) }
    }

    /**
     *  implement base subscriptions here
     */
    open fun onBindViewModel(vm: VM) {}
}