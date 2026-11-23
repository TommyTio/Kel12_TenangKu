package com.example.tommy_3tib.data

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object MoodFormat {

    private val zone = ZoneId.of("Asia/Jakarta")
    private val jamFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun formatDate(createdAt: Long): String {
        val waktu = Instant.ofEpochMilli(createdAt).atZone(zone)
        val selisihHari = ChronoUnit.DAYS.between(waktu.toLocalDate(), LocalDate.now(zone)).toInt()

        val hari = when {
            selisihHari <= 0 -> "Hari Ini"
            selisihHari == 1 -> "Kemarin"
            else -> "$selisihHari Hari Lalu"
        }
        return "$hari • ${waktu.format(jamFormat)} WIB"
    }

    fun tagList(tags: String): List<String> =
        tags.split(",")
            .map { it.trim().trimStart('#') }
            .filter { it.isNotEmpty() }
}
