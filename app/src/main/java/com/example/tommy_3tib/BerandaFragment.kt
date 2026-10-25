package com.example.tommy_3tib

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tommy_3tib.databinding.FragmentBerandafragmentBinding

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandafragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandafragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna")

        binding.tvNama.text = "Bagaimana kabarmu hari ini, $username?"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}