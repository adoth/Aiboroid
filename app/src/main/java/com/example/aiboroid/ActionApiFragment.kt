package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.aiboroid.databinding.FragmentActionApiBinding
import com.example.aiboroid.viewmodel.ActionApiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ActionApiFragment : Fragment() {

    private var _binding: FragmentActionApiBinding? = null
    private val binding get() = _binding!!
    private val arg: ActionApiFragmentArgs by navArgs()
    private val viewModel: ActionApiViewModel by viewModel { parametersOf(arg.accessToken, arg.deviceId)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActionApiBinding.inflate(inflater, container, false)
        binding.actionApiChangePostureDownButton.setOnClickListener {
            // TODO: Rename me
            viewModel.call()
        }
        return binding.root
    }
}