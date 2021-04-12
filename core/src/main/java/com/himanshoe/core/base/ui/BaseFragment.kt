package com.himanshoe.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

/**
 * Created by Himanshu Singh on 12-04-2021.
 * hello2himanshusingh@gmail.com
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater)
        setupView()
        setupObserver()
        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater): VB

    protected abstract fun setupObserver()

    protected abstract fun setupView()

    fun <T> collect(flow: StateFlow<T>, block: (T) -> Unit) {
        lifecycleScope.launchWhenResumed {
            flow.collect {
                block(it)
            }
        }
    }
}