package com.example.tommy_3tib

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tommy_3tib.data.AppDatabase
import com.example.tommy_3tib.data.MoodFormat
import com.example.tommy_3tib.data.entity.MoodEntity
import com.example.tommy_3tib.databinding.ActivityDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var db: AppDatabase
    private var mood: MoodEntity? = null

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

        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id == -1) {
            finish()
            return
        }

        db = AppDatabase.getInstance(this)
        binding.toolbar.setNavigationOnClickListener { finish() }

        lifecycleScope.launch {
            val data = db.moodDao().getById(id)
            if (data == null) {
                finish()
                return@launch
            }
            mood = data
            tampilkanData(data)
        }

        binding.btnEdit.setOnClickListener {
            Toast.makeText(this, "Kamu Menekan Edit", Toast.LENGTH_SHORT).show()
        }
        binding.btnHapus.setOnClickListener { konfirmasiHapus() }
    }

    private fun tampilkanData(data: MoodEntity) {
        val tags = MoodFormat.tagList(data.tags)

        binding.tvEmoji.text = data.emoji
        binding.tvMood.text = data.moodTitle
        binding.tvDate.text = MoodFormat.formatDate(data.createdAt)
        binding.tvLabel.text = data.moodLabel
        binding.tvNote.text = data.note
        binding.tvSaran.text = data.suggestion
        binding.tvTags.text = tags.joinToString("  ") { "#$it" }
        binding.tvTags.visibility = if (tags.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun konfirmasiHapus() {
        val data = mood ?: return

        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Catatan")
            .setMessage("Yakin ingin menghapus catatan ini?")
            .setPositiveButton("Ya") { _, _ ->
                lifecycleScope.launch {
                    db.moodDao().delete(data)
                    finish()
                }
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
