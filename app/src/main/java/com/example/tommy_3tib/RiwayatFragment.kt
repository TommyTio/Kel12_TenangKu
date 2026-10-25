package com.example.tommy_3tib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tommy_3tib.databinding.FragmentRiwayatfragmentBinding

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatfragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RiwayatAdapter(requireContext(), MoodData.entries)
        binding.listRiwayat.adapter = adapter

        binding.listRiwayat.emptyView = binding.tvKosong
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}