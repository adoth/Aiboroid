package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aiboroid.databinding.FragmentAccessTokenSettingBinding
import com.example.aiboroid.viewmodel.AccessTokenSettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccessTokenSettingFragment : Fragment() {
    private var _binding: FragmentAccessTokenSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AccessTokenSettingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccessTokenSettingBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    fun onResisterButtonClick(view: View, accessToken: String) {
        viewModel.storeAccessToken(accessToken)
        findNavController()
            .navigate(R.id.action_accessTokenSettingFragment_to_devicesFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}