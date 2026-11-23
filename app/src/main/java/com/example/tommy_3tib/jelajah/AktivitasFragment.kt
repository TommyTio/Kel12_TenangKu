package com.example.tommy_3tib.jelajah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tommy_3tib.databinding.FragmentAktivitasBinding

class AktivitasFragment : Fragment() {

    private var _binding: FragmentAktivitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAktivitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val aktivitasAdapter = AktivitasAdapter(AktivitasData.items) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Kamu memilih ${selectedItem.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvAktivitas.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = aktivitasAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}