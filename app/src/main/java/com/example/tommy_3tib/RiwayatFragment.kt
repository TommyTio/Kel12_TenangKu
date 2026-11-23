package com.example.tommy_3tib

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tommy_3tib.data.AppDatabase
import com.example.tommy_3tib.data.MoodSeeder
import com.example.tommy_3tib.data.entity.MoodEntity
import com.example.tommy_3tib.databinding.FragmentRiwayatfragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatfragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: RiwayatAdapter
    private val moods = mutableListOf<MoodEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = RiwayatAdapter(moods) { mood -> deleteMood(mood) }

        binding.rvRiwayat.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRiwayat.adapter = adapter

        binding.fabTambah.setOnClickListener {
            startActivity(Intent(requireContext(), MoodFormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchMoods()
    }

    private fun fetchMoods() {
        viewLifecycleOwner.lifecycleScope.launch {
            MoodSeeder.seedIfNeeded(requireContext(), db.moodDao())

            val result = db.moodDao().getAll()
            moods.clear()
            moods.addAll(result)
            adapter.notifyDataSetChanged()

            binding.tvKosong.visibility = if (moods.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun deleteMood(mood: MoodEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.moodDao().delete(mood)
            fetchMoods()
            Snackbar.make(binding.root, "Catatan dihapus", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fabTambah)
                .show()
        }
    }

    override fun onDestroyView() {
        binding.rvRiwayat.adapter = null
        _binding = null
        super.onDestroyView()
    }
}
