package com.example.geoapp.ui.fingerprint

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geoapp.databinding.FingerprintTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FingerprintFragment : Fragment() {

    private val viewModel: FingerprintViewModel by viewModel()
    private var binding: FingerprintTestBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FingerprintTestBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.insertBtn?.setOnClickListener {
            viewModel.saveSignal()
        }
        binding?.deleteBtn?.setOnClickListener {
            viewModel.deleteSignal()
        }
        binding?.readBtn?.setOnClickListener {
            viewModel.getAllSignals()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
