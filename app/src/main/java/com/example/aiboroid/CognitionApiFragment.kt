package com.example.aiboroid

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CognitionApiFragment : Fragment() {

    companion object {
        fun newInstance() = CognitionApiFragment()
    }

    private lateinit var viewModel: CognitionApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cognition_api, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CognitionApiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}