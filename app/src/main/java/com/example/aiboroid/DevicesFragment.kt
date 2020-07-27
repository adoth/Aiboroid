package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aiboroid.adapter.CardRecyclerAdapter
import com.example.aiboroid.databinding.FragmentDevicesBinding
import com.example.aiboroid.viewmodel.DevicesViewModel

class DevicesFragment : Fragment() {
    private var _binding: FragmentDevicesBinding? = null
    private val binding get() = _binding!!
    private var viewModel = DevicesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDevicesBinding.inflate(inflater, container, false)
        viewModel.getAccessToken()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.accessToken.observe(viewLifecycleOwner, Observer {
            viewModel.getDeviceApi()
        })
        viewModel.devices.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.INVISIBLE
            binding.deviceRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.deviceRecyclerView.adapter = CardRecyclerAdapter(viewModel.devices.value!!)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}