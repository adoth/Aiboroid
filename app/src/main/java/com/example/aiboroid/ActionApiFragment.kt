package com.example.aiboroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.aiboroid.adapter.SingleParameterAdapter
import com.example.aiboroid.databinding.FragmentActionApiBinding
import com.example.aiboroid.model.ChangePosture
import com.example.aiboroid.model.SetMode
import com.example.aiboroid.viewmodel.ActionApiViewModel
import com.example.aiboroid.viewmodel.ShareIdViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ActionApiFragment : Fragment() {

    private var _binding: FragmentActionApiBinding? = null
    private val binding get() = _binding!!
    private val shareViewMode: ShareIdViewModel by activityViewModels()
    private val viewModel: ActionApiViewModel by viewModel { parametersOf(shareViewMode.accessToken, shareViewMode.deviceId)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActionApiBinding.inflate(inflater, container, false)
        setSetModeAdapter()
        setChangePostureAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.executionState.observe(viewLifecycleOwner, Observer { executionState ->
            when(executionState) {
                ActionApiViewModel.ExecutionState.SUCCEEDED -> Toast.makeText(requireContext(), "成功！", Toast.LENGTH_SHORT).show()
                ActionApiViewModel.ExecutionState.FAILED -> Toast.makeText(requireContext(), "失敗！", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(requireContext(), "不明", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setSetModeAdapter() {
        val setModes = listOf(SetMode("まて", SetMode.SetModeType.DEVELOPMENT), SetMode("やめる", SetMode.SetModeType.NORMAL))
        val adapter = object : SingleParameterAdapter(setModes) {
            override fun onApiClicked(parameter: String) {
                super.onApiClicked(parameter)
                // TODO: call SetMode api
            }
        }
        binding.setModeRecycler.adapter = adapter
    }

    private fun setChangePostureAdapter() {
        val changePostures = listOf(ChangePosture("おすわり", ChangePosture.ChangePostureType.sit))
        val adapter = object : SingleParameterAdapter(changePostures) {
            override fun onApiClicked(parameter: String) {
                super.onApiClicked(parameter)
                // TODO: call ChangePosture api
            }
        }
        binding.changePostureRecycler.adapter = adapter
    }
}