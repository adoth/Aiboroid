package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aiboroid.databinding.FragmentSelectApiTypeBinding
import com.example.aiboroid.viewmodel.SelectApiTypeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectApiTypeFragment : Fragment() {

    private var _binding: FragmentSelectApiTypeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SelectApiTypeViewModel by viewModel()
    private val args: SelectApiTypeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectApiTypeBinding.inflate(inflater, container, false)
        binding.actionApiButton.setOnClickListener {
            findNavController().navigate(R.id.action_selectApiTypeFragment_to_tabFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}