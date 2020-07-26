package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.aiboroid.databinding.FragmentDevicesBinding
import com.example.aiboroid.viewmodel.DevicesViewModel
import kotlinx.android.synthetic.main.fragment_devices.*

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
            textView.text = viewModel.devices.value?.get(0)?.nickname
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}