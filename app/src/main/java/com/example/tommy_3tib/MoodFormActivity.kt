package com.example.tommy_3tib

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tommy_3tib.data.AppDatabase
import com.example.tommy_3tib.data.entity.MoodEntity
import com.example.tommy_3tib.databinding.ActivityMoodFormBinding
import kotlinx.coroutines.launch

class MoodFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoodFormBinding
    private lateinit var db: AppDatabase

    private class PilihanMood(
        val emoji: String,
        val label: String,
        val saran: String
    )

    private fun pilihanDari(chipId: Int): PilihanMood? = when (chipId) {
        R.id.chipSenang -> PilihanMood(
            "😊", "Baik",
            "Rayakan momen ini. Simpan catatan ini untuk dibaca lagi saat hari terasa berat."
        )
        R.id.chipTenang -> PilihanMood(
            "😌", "Baik",
            "Pertahankan ritme ini. Beri dirimu waktu untuk menikmati rasa tenang."
        )
        R.id.chipBiasa -> PilihanMood(
            "😐", "Netral",
            "Hari yang biasa saja juga berharga. Pelan-pelan saja."
        )
        R.id.chipCemas -> PilihanMood(
            "😰", "Cemas",
            "Tarik napas perlahan. Pecah masalahmu jadi bagian kecil dan selesaikan satu per satu."
        )
        R.id.chipSedih -> PilihanMood(
            "😔", "Sedih",
            "Wajar merasa sedih. Ceritakan pada orang yang kamu percaya, kamu tidak sendirian."
        )
        R.id.chipLelah -> PilihanMood(
            "😴", "Lelah",
            "Tubuh dan pikiranmu butuh istirahat. Coba tidur lebih awal malam ini."
        )
        else -> null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMoodFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnSaveNote.setOnClickListener {
            val pilihan = pilihanDari(binding.chipGroupMood.checkedChipId)
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()
            val tags = binding.etTags.text.toString().trim()

            if (pilihan == null || title.isBlank() || content.isBlank()) {
                Toast.makeText(
                    this, "Pilih mood, lalu isi judul dan cerita!", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            binding.btnSaveNote.isEnabled = false

            lifecycleScope.launch {
                try {
                    db.moodDao().insert(
                        MoodEntity(
                            emoji = pilihan.emoji,
                            moodTitle = title,
                            moodLabel = pilihan.label,
                            note = content,
                            tags = tags,
                            suggestion = pilihan.saran
                        )
                    )
                    finish()
                } catch (e: Exception) {
                    binding.btnSaveNote.isEnabled = true
                    Toast.makeText(
                        this@MoodFormActivity, "Gagal menyimpan catatan", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
