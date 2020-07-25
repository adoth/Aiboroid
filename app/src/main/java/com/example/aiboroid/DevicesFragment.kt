package com.example.aiboroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.aiboroid.databinding.FragmentDevicesBinding
import com.example.aiboroid.repository.AccessTokenRepository
import com.example.aiboroid.viewmodel.DevicesViewModel
import kotlinx.android.synthetic.main.fragment_devices.*

class DevicesFragment : Fragment() {

    private var viewModel = DevicesViewModel()
    private val accessTokenRepository = AccessTokenRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.accessToken.observe(viewLifecycleOwner, Observer {
            tmp.text = "hogehoge"
        })

        button.setOnClickListener {
            viewModel.getAccessToken()
        }
    }


}