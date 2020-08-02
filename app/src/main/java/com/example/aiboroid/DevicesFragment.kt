package com.example.aiboroid

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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
                DevicesViewModel.AccessTokenState.NOT_YET -> findNavController().navigate(R.id.accessTokenSettingFragment)
                DevicesViewModel.AccessTokenState.INVALID -> {
                    this.viewModel.deleteAccessToken()
                }
                else ->  findNavController().navigate(R.id.accessTokenSettingFragment)
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel.devices.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.INVISIBLE
            binding.deviceRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.deviceRecyclerView.adapter = CardRecyclerAdapter(this.viewModel.devices.value!!)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}