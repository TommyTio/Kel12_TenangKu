package com.example.tommy_3tib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tommy_3tib.databinding.ItemRiwayatBinding
import com.google.android.material.snackbar.Snackbar

class RiwayatAdapter(
    context: Context,
    private val entries: List<MoodModel>
) : ArrayAdapter<MoodModel>(context, 0, entries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            ItemRiwayatBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemRiwayatBinding.bind(convertView)
        }

        val data = entries[position]

        binding.tvEmoji.text = data.emoji
        binding.tvMood.text = data.moodTitle
        binding.tvDate.text = data.dateTime
        binding.tvLabel.text = data.moodLabel
        binding.tvNote.text = data.note
        binding.tvTags.text = data.tags.joinToString("  ") { "#$it" }

        binding.root.setOnClickListener {
            Toast.makeText(context, data.moodTitle, Toast.LENGTH_SHORT).show()
        }

        binding.btnEdit.setOnClickListener {
            Toast.makeText(context, "Edit ditekan", Toast.LENGTH_SHORT).show()
        }

        binding.btnHapus.setOnClickListener {
            Snackbar.make(binding.root, "Item dihapus",
                Snackbar.LENGTH_LONG)
                .setAction("BATAL") {
                }
                .show()
        }

        return binding.root
    }
}