package com.example.aiboroid

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aiboroid.adapter.CardRecyclerAdapter
import com.example.aiboroid.databinding.FragmentDevicesBinding
import com.example.aiboroid.viewmodel.DevicesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DevicesFragment : Fragment() {
    private var _binding: FragmentDevicesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DevicesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        this.viewModel.accessTokenState.observe(viewLifecycleOwner, Observer { accessTokenState ->
            when (accessTokenState) {
                DevicesViewModel.AccessTokenState.SAVED -> this.viewModel.getDeviceApi()
                DevicesViewModel.AccessTokenState.NOT_YET -> findNavController().navigate(R.id.action_devicesFragment_to_accessTokenSettingFragment)
                DevicesViewModel.AccessTokenState.INVALID -> {
                    this.viewModel.deleteAccessToken()
                    showInvalidAccessTokenDialog()
                }
                DevicesViewModel.AccessTokenState.EXCEED_RATE_LIMIT -> showExceedRateLimitDialog()
                DevicesViewModel.AccessTokenState.SERVER_ERROR -> showServerErrorDialog()
                else -> findNavController().navigate(R.id.action_devicesFragment_to_accessTokenSettingFragment)
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel.devices.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.INVISIBLE
            binding.deviceRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.deviceRecyclerView.adapter = object : CardRecyclerAdapter(this.viewModel.devices.value!!) {
                @Override
                override fun onDeviceClicked(deviceId: String) {
                    val action = DevicesFragmentDirections.actionDevicesFragmentToSelectApiTypeFragment(viewModel.accessToken.value!!, deviceId)
                    findNavController().navigate(action)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showInvalidAccessTokenDialog() {
        AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setMessage(R.string.dialog_access_token_invalid)
            .setPositiveButton(R.string.dialog_access_token_invalid_positive, DialogInterface.OnClickListener { _, _ ->
                findNavController().navigate(R.id.action_devicesFragment_to_accessTokenSettingFragment)
            })
            .setOnCancelListener { findNavController().navigate(R.id.action_devicesFragment_to_accessTokenSettingFragment) }
            .show()
    }

    private fun showExceedRateLimitDialog() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setMessage(R.string.dialog_exceed_rate_limit)
            .show()
    }

    private fun showServerErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setMessage(R.string.dialog_server_error)
            .show()
    }

}