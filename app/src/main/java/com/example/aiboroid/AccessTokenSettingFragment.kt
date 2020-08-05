package com.example.aiboroid

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onPasteClipBoardButtonClick() {
        val clipboard =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (!clipboard.hasPrimaryClip()) {
            return
        }

        val item = clipboard.primaryClip!!.getItemAt(0)
        val clipboardText = item.text.toString()
        if (viewModel.invalidAccessToken(clipboardText)) {
            showInvalidAccessToken()
            return
        }
        binding.inputAccessToken.setText(clipboardText)
        binding.accessTokenRegisterButton.isEnabled = true
    }

    fun onResisterButtonClick(accessToken: String) {
        if (TextUtils.isEmpty(accessToken)) {
            showNeedAccessTokenToast()
            return
        } else if (viewModel.invalidAccessToken(accessToken)) {
            showInvalidAccessToken()
            return
        }
        viewModel.storeAccessToken(accessToken)
        findNavController()
            .navigate(R.id.action_accessTokenSettingFragment_to_devicesFragment)
    }

    private fun showNeedAccessTokenToast() {
        Toast.makeText(
            requireContext(),
            R.string.access_token_toast_empty_access_token,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showInvalidAccessToken() {
        Toast.makeText(
            requireContext(),
            R.string.access_token_toast_invalid_access_token,
            Toast.LENGTH_SHORT
        ).show()
    }
}