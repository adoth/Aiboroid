package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aiboroid.viewmodel.SelectApiTypeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectApiTypeFragment : Fragment() {

    private val viewModel: SelectApiTypeViewModel by viewModel()
    val args: SelectApiTypeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_api_type, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}