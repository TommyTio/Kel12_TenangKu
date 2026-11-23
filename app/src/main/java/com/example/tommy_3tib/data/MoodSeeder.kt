package com.example.tommy_3tib.data

import android.content.Context
import com.example.tommy_3tib.data.dao.MoodDao
import com.example.tommy_3tib.data.entity.MoodEntity
import java.time.ZoneId
import java.time.ZonedDateTime

object MoodSeeder {

    suspend fun seedIfNeeded(context: Context, dao: MoodDao) {
        val pref = context.getSharedPreferences("mood_seed", Context.MODE_PRIVATE)
        if (pref.getBoolean("sudah_diisi", false)) return

        if (dao.count() == 0) {
            dao.insertAll(dataContoh())
        }
        pref.edit().putBoolean("sudah_diisi", true).apply()
    }

    private fun waktu(hariLalu: Int, jam: Int, menit: Int): Long =
        ZonedDateTime.now(ZoneId.of("Asia/Jakarta"))
            .minusDays(hariLalu.toLong())
            .withHour(jam).withMinute(menit).withSecond(0).withNano(0)
            .toInstant().toEpochMilli()

    private fun dataContoh() = listOf(
        MoodEntity(
            emoji = "😊", moodTitle = "Tenang & Puas", moodLabel = "Baik",
            note = "Bimbingan skripsi berjalan lancar, dosennya suportif banget. Lega rasanya bab 3 diterima.",
            tags = "Skripsi,Bimbingan,MeTime",
            suggestion = "Rayakan langkah kecil ini. Beri dirimu waktu istirahat sebelum lanjut ke bab berikutnya.",
            createdAt = waktu(1, 20, 15)
        ),
        MoodEntity(
            emoji = "😐", moodTitle = "Biasa Saja", moodLabel = "Netral",
            note = "Hari kuliah yang lumayan padat. Agak ngantuk di kelas siang, tapi sempat ngopi bareng teman.",
            tags = "Kuliah,Kopi,Teman",
            suggestion = "Hari yang biasa saja juga berharga. Obrolan santai dengan teman bisa jadi pengisi tenaga.",
            createdAt = waktu(2, 16, 30)
        ),
        MoodEntity(
            emoji = "😔", moodTitle = "Kelelahan Mental", moodLabel = "Cemas",
            note = "Panik pas tugas kelompok deadline-nya mepet. Komunikasi tim agak macet. Butuh tidur lebih.",
            tags = "Tugas,Deadline,Tidur",
            suggestion = "Wajar merasa lelah saat tenggat mepet. Tarik napas perlahan, bagi tugas jadi bagian kecil, dan prioritaskan tidur malam ini.",
            createdAt = waktu(3, 22, 40)
        ),
        MoodEntity(
            emoji = "🤩", moodTitle = "Gembira", moodLabel = "Sangat Baik",
            note = "Presentasi kelompok lancar dan dapat pujian dari dosen. Timnya akhirnya kompak.",
            tags = "Kuliah,Presentasi,Teman",
            suggestion = "Simpan momen ini. Saat hari terasa berat, kamu bisa membaca catatan ini lagi.",
            createdAt = waktu(4, 19, 5)
        ),
        MoodEntity(
            emoji = "😐", moodTitle = "Datar", moodLabel = "Netral",
            note = "Bangun kesiangan tapi masih sempat sarapan. Hari berjalan datar saja.",
            tags = "Pagi,Sarapan",
            suggestion = "Tidak apa-apa kalau hari terasa datar. Pelan-pelan saja.",
            createdAt = waktu(5, 8, 20)
        ),
        MoodEntity(
            emoji = "😔", moodTitle = "Sedikit Cemas", moodLabel = "Cemas",
            note = "Uang saku menipis sebelum akhir bulan, jadi kepikiran terus.",
            tags = "Finansial,UangSaku",
            suggestion = "Mencatat pengeluaran kecil bisa membantu terasa lebih terkendali. Kamu tidak sendirian soal ini.",
            createdAt = waktu(6, 21, 10)
        ),
        MoodEntity(
            emoji = "😊", moodTitle = "Tenang", moodLabel = "Baik",
            note = "Sore ini jalan kaki sebentar keliling kampus. Pikiran terasa lebih ringan.",
            tags = "MeTime,Istirahat,Kampus",
            suggestion = "Jeda singkat seperti ini ampuh. Coba jadikan kebiasaan di sela jadwal kuliah.",
            createdAt = waktu(7, 18, 45)
        )
    )
}
