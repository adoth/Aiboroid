package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aiboroid.databinding.FragmentAccessTokenSettingBinding
import com.example.aiboroid.viewmodel.AccessTokenSettingViewModel


class AccessTokenSettingFragment : Fragment() {
    private var _binding: FragmentAccessTokenSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel = AccessTokenSettingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccessTokenSettingBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    fun onResisterButtonClick(accessToken: String) {
        viewModel.storeAccessToken(accessToken)
        val activity = activity as MainActivity
        activity.replaceFragment(DevicesFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}