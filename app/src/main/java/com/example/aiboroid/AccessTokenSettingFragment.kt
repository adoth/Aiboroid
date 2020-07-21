package com.example.aiboroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.aiboroid.databinding.FragmentAccessTokenSettingBinding
import com.example.aiboroid.viewmodel.AccessTokenSettingViewModel
import kotlinx.android.synthetic.main.fragment_access_token_setting.*
import org.koin.android.ext.android.bind


class AccessTokenSettingFragment : Fragment() {
    private lateinit var binding: FragmentAccessTokenSettingBinding
    private val viewModel = AccessTokenSettingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccessTokenSettingBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }
}