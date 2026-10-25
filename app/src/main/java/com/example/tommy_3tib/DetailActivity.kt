package com.example.tommy_3tib

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tommy_3tib.databinding.ActivityDetailBinding
import com.example.tommy_3tib.databinding.DialogEditCatatanBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }

    private lateinit var binding: ActivityDetailBinding
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        position = intent.getIntExtra(EXTRA_POSITION, -1)
        if (position !in MoodData.entries.indices) {
            finish()
            return
        }

        binding.toolbar.setNavigationOnClickListener { finish() }

        tampilkanData()

        binding.btnEdit.setOnClickListener { tampilkanDialogEdit() }
        binding.btnHapus.setOnClickListener { konfirmasiHapus() }
    }

    private fun tampilkanData() {
        val entry = MoodData.entries[position]

        binding.tvEmoji.text = entry.emoji
        binding.tvMood.text = entry.moodTitle
        binding.tvDate.text = entry.dateTime
        binding.tvLabel.text = entry.moodLabel
        binding.tvNote.text = entry.note
        binding.tvSaran.text = entry.suggestion

        binding.tvTags.text = entry.tags.joinToString("  ") { "#$it" }
        binding.tvTags.visibility = if (entry.tags.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun tampilkanDialogEdit() {
        Toast.makeText(this, "Kamu Menekan Edit", Toast.LENGTH_SHORT).show()
    }

    private fun konfirmasiHapus() {
        Snackbar.make(binding.main, "Hapus catatan ini?", Snackbar.LENGTH_LONG)
            .setAction("Cancel") {
                Snackbar.make(
                    binding.main,
                    "",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .show()
    }
}