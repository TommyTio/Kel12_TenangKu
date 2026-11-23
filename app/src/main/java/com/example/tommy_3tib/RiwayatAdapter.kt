package com.example.tommy_3tib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tommy_3tib.data.MoodFormat
import com.example.tommy_3tib.data.entity.MoodEntity
import com.example.tommy_3tib.databinding.ItemRiwayatBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RiwayatAdapter(
    private val moods: List<MoodEntity>,
    private val onDelete: (MoodEntity) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(val binding: ItemRiwayatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val binding = ItemRiwayatBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RiwayatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val mood = moods[position]
        val tags = MoodFormat.tagList(mood.tags)

        with(holder.binding) {
            tvEmoji.text = mood.emoji
            tvMood.text = mood.moodTitle
            tvDate.text = MoodFormat.formatDate(mood.createdAt)
            tvLabel.text = mood.moodLabel
            tvNote.text = mood.note
            tvTags.text = tags.joinToString("  ") { "#$it" }
            tvTags.visibility = if (tags.isEmpty()) View.GONE else View.VISIBLE

            root.setOnClickListener {
                Toast.makeText(root.context, mood.moodTitle, Toast.LENGTH_SHORT).show()
            }

            btnEdit.setOnClickListener {
                Toast.makeText(root.context, "Edit ditekan", Toast.LENGTH_SHORT).show()
            }

            btnHapus.setOnClickListener {
                MaterialAlertDialogBuilder(root.context)
                    .setTitle("Hapus Catatan")
                    .setMessage("Yakin ingin menghapus catatan ini?")
                    .setPositiveButton("Ya") { _, _ -> onDelete(mood) }
                    .setNegativeButton("Batal", null)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = moods.size
}
